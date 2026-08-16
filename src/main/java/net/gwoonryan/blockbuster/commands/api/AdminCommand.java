package net.gwoonryan.blockbuster.commands.api;

import net.gwoonryan.blockbuster.game.player.BBPlayer;
import net.gwoonryan.blockbuster.game.player.PlayerManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AdminCommand extends Command {
    public AdminCommand(String command) {
        super(command);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command var2, String var3, String[] args){
        if (sender instanceof Player player) {
            BBPlayer bp = PlayerManager.getPlayer(player);
            if (bp.isAdmin()) {
                runCommand(player, bp, args);
            } else {
                bp.messageNoPermissionCommand();
            }
        }
        return true;
    }

}
