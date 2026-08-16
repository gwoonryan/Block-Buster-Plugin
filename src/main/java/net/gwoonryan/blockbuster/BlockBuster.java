package net.gwoonryan.blockbuster;

import org.bukkit.plugin.java.JavaPlugin;

public final class BlockBuster extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("yo");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
