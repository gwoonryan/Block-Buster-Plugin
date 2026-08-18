package net.gwoonryan.blockbuster;

import net.gwoonryan.blockbuster.commands.api.CommandManager;
import net.gwoonryan.blockbuster.events.ItemEvents;
import net.gwoonryan.blockbuster.events.ServerEvents;
import net.gwoonryan.blockbuster.game.item.api.ItemManager;
import net.gwoonryan.blockbuster.game.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.logging.Logger;

public final class BlockBuster extends JavaPlugin {

    public static Logger logger;
    private static BlockBuster instance;
    public static Random rnd;

    public static String server_name_abbreviation = "BB";

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = getLogger();
        instance = this;
        rnd = new Random();
        getLogger().info("Starting BlockBuster");
        PlayerManager.init();
        ItemManager.init();
        CommandManager.init();
        Bukkit.getPluginManager().registerEvents(new ServerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ItemEvents(), this);
        autoReload();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PlayerManager.stop();
        instance = null;
    }

    public static BlockBuster getPlugin() {
        return instance;
    }

    private void autoReload(){
        final long lastModified = getFile().lastModified();

        new BukkitRunnable() {
            public void run() {
                if (getFile().lastModified() > lastModified) {
                    cancel();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "plugman reload Block-Buster");
                }
            }
        }.runTaskTimer(this, 0, 20);
    }
}
