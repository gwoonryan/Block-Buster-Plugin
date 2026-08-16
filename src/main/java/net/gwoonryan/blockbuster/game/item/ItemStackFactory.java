package net.gwoonryan.blockbuster.game.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

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
            meta.setDisplayName(definition.getDisplayName());
        }

//        if (definition.lore() != null
//                && !definition.lore().isEmpty()) {
//            meta.setLore(definition.lore());
//        }

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
