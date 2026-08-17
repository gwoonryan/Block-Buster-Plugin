package net.gwoonryan.blockbuster.game.item;

import net.gwoonryan.blockbuster.BlockBuster;
import net.gwoonryan.blockbuster.game.item.components.MeleeWeaponComponent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.List;

public class ItemBuilder {

    private static NamespacedKey key(String key){
        return new NamespacedKey(BlockBuster.getPlugin(), key);
    }

    public static void build(){
        ItemManager.registerItem(new ItemDefinition(
                key("steel_sword"),
                Material.IRON_SWORD,
                "Steel Sword",
                List.of(
                    new MeleeWeaponComponent(4, 1.2)
                )
        ));
    }
}
