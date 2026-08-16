package net.gwoonryan.blockbuster;

import lombok.Getter;
import net.gwoonryan.blockbuster.commands.api.CommandManager;
import net.gwoonryan.blockbuster.event.Events;
import net.gwoonryan.blockbuster.game.item.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class BlockBuster extends JavaPlugin {

    public static Logger logger;
    private static BlockBuster instance;

    public static String server_name_abbreviation = "BB";

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = getLogger();
        instance = this;
        getLogger().info("Starting BlockBuster");
        ItemManager.init();
        CommandManager.init();
        Bukkit.getPluginManager().registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
    }

    public static BlockBuster getPlugin() {
        return instance;
    }
}
