package net.gwoonryan.blockbuster.game.item.api.components;

import net.gwoonryan.blockbuster.game.item.api.context.ItemUseContext;

public interface UseComponent extends net.gwoonryan.blockbuster.game.item.api.components.ItemComponent {
    void onUse(ItemUseContext context);
}