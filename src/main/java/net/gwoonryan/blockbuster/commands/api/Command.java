package net.gwoonryan.blockbuster.commands.api;

import net.gwoonryan.blockbuster.BlockBuster;
import net.gwoonryan.blockbuster.game.player.BBPlayer;
import net.gwoonryan.blockbuster.game.player.PlayerManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Command implements CommandExecutor, TabCompleter {

    public Command(String command){
        BlockBuster.getPlugin().getCommand(command).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command var2, String var3, String[] args){
        if (sender instanceof Player player) {
            BBPlayer bp = PlayerManager.getPlayer(player);
            runCommand(player, bp, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        if (sender instanceof Player player) {
            BBPlayer bp = PlayerManager.getPlayer(player);
            return tabComplete(player, bp, args);
        }
        return null;
    }

    public abstract void runCommand(Player player, BBPlayer bp, String[] args);

    public abstract List<String> tabComplete(Player player, BBPlayer bp, String[] args);
}
