package net.gwoonryan.blockbuster.game.item.api.components;

import net.gwoonryan.blockbuster.game.item.api.LoreSection;
import net.gwoonryan.blockbuster.game.item.api.context.LoreContext;
import net.kyori.adventure.text.Component;

import java.util.List;

public interface LoreComponent extends net.gwoonryan.blockbuster.game.item.api.components.ItemComponent {

    LoreSection loreSection();

    List<Component> getLore(LoreContext context);
}
