package net.gwoonryan.blockbuster.game.player;

import lombok.Getter;
import net.gwoonryan.blockbuster.BlockBuster;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BBPlayer {
    @Getter
    private final UUID uuid;
    BBPlayer(Player player) {
        uuid  = player.getUniqueId();
    }

    public Player getBukkitPlayer(){
        return Bukkit.getPlayer(uuid);
    }

    public boolean isAdmin(){
        Player player = getBukkitPlayer();
        return player.hasPermission("bb.admin");
    }
    public void sendMessage(String message){
        getBukkitPlayer().sendMessage("§f["+ BlockBuster.server_name_abbreviation+"§f] §r" + message);
    }

    public void sendErrorMessage(String message){
        getBukkitPlayer().sendMessage(NamedTextColor.RED + "["+"§x§8§0§0§0§0§0E§x§a§0§0§0§0§0r§x§c§0§0§0§0§0r§x§e§0§0§0§0§0o§x§f§f§0§0§0§0r"+ChatColor.RED + "] " + message);
    }

    public void messageNoPermissionUse(){
        sendMessage(NamedTextColor.RED + "You do not have permission to use that!");
    }

    public void messageNoPermissionCommand(){
        sendMessage(NamedTextColor.RED + "You do not have permission to perform that command!");
    }

    public void playSound(Sound sound, float volume, float pitch){
        getBukkitPlayer().playSound(getBukkitPlayer().getLocation(), sound, volume, pitch);
    }

    public void playOkaySound(){
        playSound(Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 2f);
    }

    public void playNoSound(){
        playSound(Sound.ENTITY_VILLAGER_NO, 1f, 1f);
    }
}
