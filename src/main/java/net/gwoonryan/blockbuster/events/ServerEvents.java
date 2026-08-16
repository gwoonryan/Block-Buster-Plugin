package net.gwoonryan.blockbuster.events;

import net.gwoonryan.blockbuster.game.player.BBPlayer;
import net.gwoonryan.blockbuster.game.player.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ServerEvents implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        BBPlayer gp = PlayerManager.getPlayer(e.getPlayer());
        gp.sendToSpawn();
        gp.sendMessage("Welcome " + e.getPlayer().getName() +"§r!" );
//        NpcManager.spawnAllNpcsForPlayer(gp.getBukkitPlayer());
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        PlayerManager.unloadPlayer(e.getPlayer());
    }
}
