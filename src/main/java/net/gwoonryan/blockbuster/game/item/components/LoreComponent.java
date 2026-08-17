package net.gwoonryan.blockbuster.game.item.components;

import net.gwoonryan.blockbuster.game.item.LoreSection;
import net.gwoonryan.blockbuster.game.item.context.LoreContext;
import net.kyori.adventure.text.Component;

import java.util.List;

public interface LoreComponent extends ItemComponent {

    LoreSection loreSection();

    List<Component> getLore(LoreContext context);
}
