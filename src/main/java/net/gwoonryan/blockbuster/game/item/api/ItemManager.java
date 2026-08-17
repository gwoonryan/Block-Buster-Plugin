package net.gwoonryan.blockbuster.game.item.api;

import lombok.Getter;
import net.gwoonryan.blockbuster.BlockBuster;
import net.gwoonryan.blockbuster.game.item.implementation.ItemBuilder;
import net.gwoonryan.blockbuster.game.item.implementation.Weapons;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Optional;

public class ItemManager {
    @Getter
    public static HashMap<NamespacedKey, ItemDefinition> registeredItems = new HashMap<>();

    public static void init(){
        BlockBuster.logger.info("Registering Items");
        ItemBuilder.init();
    }

    public static void registerItem(ItemDefinition item) {
        if (!registeredItems.containsKey(item.getId())){
            registeredItems.put(item.getId(), item);
        }else{
            BlockBuster.logger.warning("Item ID '" + item.getId().toString() + "' is already registered!");
        }
    }

    public static Optional<ItemDefinition> getDefinition(NamespacedKey key){
        return Optional.ofNullable(registeredItems.get(key));
    }

    public static Optional<ItemDefinition> getDefinition(String id){
        return Optional.ofNullable(registeredItems.get(new NamespacedKey(BlockBuster.getPlugin(), id)));
    }

    public static Optional<CustomItemView> resolve(ItemStack stack) {
        if (stack == null || stack.getType().isAir()) {
            return Optional.empty();
        }

        ItemMeta meta = stack.getItemMeta();
        if (meta == null) {
            return Optional.empty();
        }

        String rawId = meta.getPersistentDataContainer().get(
                ItemDataKeys.ITEM_ID.getKey(),
                PersistentDataType.STRING
        );

        if (rawId == null) {
            return Optional.empty();
        }

        NamespacedKey id = NamespacedKey.fromString(rawId);

        if (id == null) {
            return Optional.empty();
        }

        ItemDefinition definition = registeredItems.get(id);

        if (definition == null) {
            return Optional.empty();
        }

        return Optional.of(
                new CustomItemView(stack, definition)
        );
    }
}
