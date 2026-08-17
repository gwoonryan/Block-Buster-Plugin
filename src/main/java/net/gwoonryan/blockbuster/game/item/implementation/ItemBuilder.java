package net.gwoonryan.blockbuster.game.item.implementation;

import net.gwoonryan.blockbuster.BlockBuster;

public class ItemBuilder {
    public static void init(){
        BlockBuster.logger.info("ItemBuilder building all items");
        Weapons.build();
    }
}
