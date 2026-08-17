package net.gwoonryan.blockbuster.game.item;

import net.gwoonryan.blockbuster.game.item.components.LoreComponent;
import net.gwoonryan.blockbuster.game.item.context.LoreContext;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class ItemStackFactory {

    private static final int CURRENT_DATA_VERSION = 1;

    public static ItemStack create(ItemDefinition definition) {
        return create(definition, 1);
    }

    public static ItemStack create(ItemDefinition definition, int amount) {

        ItemStack stack = new ItemStack(
                definition.getMaterial(),
                amount
        );

        ItemMeta meta = stack.getItemMeta();

        if (meta == null) {
            throw new IllegalStateException(
                    "Material " + definition.getMaterial()
                            + " does not support ItemMeta"
            );
        }

        applyDefinition(definition, meta);

        stack.setItemMeta(meta);

        return stack;
    }

    private static void applyDefinition(
            ItemDefinition definition,
            ItemMeta meta
    ) {

        // Visual data
        if (definition.getDisplayName() != null) {
            meta.setDisplayName("§r"+definition.getDisplayName());
        }

        ArrayList<LoreComponent> components = new ArrayList<>(definition.allComponentsOfType(LoreComponent.class));
        components.sort(Comparator.comparingInt(component -> component.loreSection().order()));

        List<Component> lore = new ArrayList<>();

        LoreContext context = new LoreContext(definition);

        for (LoreComponent component : components) {
            List<Component> lines = component.getLore(context);

            if (lines.isEmpty()) {
                continue;
            }

//            if (!lore.isEmpty()) {
//                lore.add("");
//            }

            lore.addAll(lines);
        }

        meta.lore(lore);

        // Custom item identity
        PersistentDataContainer pdc =
                meta.getPersistentDataContainer();

        pdc.set(
                ItemDataKeys.ITEM_ID.getKey(),
                PersistentDataType.STRING,
                definition.getId().toString()
        );

        pdc.set(
                ItemDataKeys.DATA_VERSION.getKey(),
                PersistentDataType.INTEGER,
                CURRENT_DATA_VERSION
        );
    }
}
