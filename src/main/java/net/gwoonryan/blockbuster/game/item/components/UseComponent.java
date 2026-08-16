package net.gwoonryan.blockbuster.game.item.components;

import net.gwoonryan.blockbuster.game.item.context.ItemUseContext;

public interface UseComponent extends ItemComponent {
    void onUse(ItemUseContext context);
}