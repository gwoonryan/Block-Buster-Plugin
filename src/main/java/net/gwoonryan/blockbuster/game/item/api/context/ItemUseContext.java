package net.gwoonryan.blockbuster.game.item.api.context;

import net.gwoonryan.blockbuster.game.item.api.CustomItemView;
import net.gwoonryan.blockbuster.game.player.BBPlayer;
import org.bukkit.event.block.Action;

public record ItemUseContext(
        BBPlayer player,
        CustomItemView item,
        Action action
) {}
