package net.gwoonryan.blockbuster.game.player;

import net.gwoonryan.blockbuster.BlockBuster;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private static final HashMap<UUID, BBPlayer> players = new HashMap<>();

    public static BBPlayer getPlayer(Player player){
        if (player == null) return null;
        if (players.containsKey(player.getUniqueId())) {
            return players.get(player.getUniqueId());
        }
        BBPlayer gp = new BBPlayer(player);
        players.put(player.getUniqueId(), gp);
        return gp;
    }

    public static BBPlayer getPlayer(UUID uuid){
        return getPlayer(Bukkit.getPlayer(uuid));
    }

    public static void unloadPlayer(Player player){
        if (players.containsKey(player.getUniqueId())) {
            players.get(player.getUniqueId()).unloadPlayer();
            players.remove(player.getUniqueId());
        }
    }

    public static void stop(){
        for (BBPlayer player : players.values()){
            player.unloadPlayer();
        }
        players.clear();
    }

    public static void init(){
        BlockBuster.logger.info("Player manager starting.");
        for (Player player : Bukkit.getOnlinePlayers()){
            getPlayer(player);
        }
    }

    public static Collection<BBPlayer> getAllPlayers(){
        return players.values();
    }

}
