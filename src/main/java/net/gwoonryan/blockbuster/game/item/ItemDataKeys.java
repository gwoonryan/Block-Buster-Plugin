package net.gwoonryan.blockbuster.game.item;

import lombok.Getter;
import net.gwoonryan.blockbuster.BlockBuster;
import org.bukkit.NamespacedKey;

public enum ItemDataKeys {
    ITEM_ID(new NamespacedKey(BlockBuster.getPlugin(), "item_id")),
    DATA_VERSION(new NamespacedKey(BlockBuster.getPlugin(), "data_version"))
    ;
    @Getter
    private NamespacedKey key;
    ItemDataKeys(NamespacedKey key){
        this.key = key;
    }
}
