package net.gwoonryan.blockbuster.game.item.implementation;

import net.gwoonryan.blockbuster.BlockBuster;
import net.gwoonryan.blockbuster.game.item.api.ItemDefinition;
import net.gwoonryan.blockbuster.game.item.api.ItemManager;
import net.gwoonryan.blockbuster.game.item.api.components.MeleeWeaponComponent;
import net.gwoonryan.blockbuster.game.item.api.components.UseComponent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.List;

public class Weapons {

    private static NamespacedKey key(String key){
        return new NamespacedKey(BlockBuster.getPlugin(), key);
    }

    public static void build(){
        ItemManager.registerItem(new ItemDefinition(
                key("steel_sword"),
                Material.IRON_SWORD,
                "Steel Sword",
                List.of(
                        new MeleeWeaponComponent(4, 1.2),
                        (UseComponent) context -> context.player().sendMessage("click!")
                )
        ));
    }
}
