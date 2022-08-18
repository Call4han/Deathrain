package me.callahandevelopment.deathrain;

import me.callahandevelopment.deathrain.commands.commandsdr;
import me.callahandevelopment.deathrain.listeners.ThunderListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathRain extends JavaPlugin {
    public static DeathRain plugin;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ThunderListener(this),this);
        this.getCommand("Deathrain").setExecutor(new commandsdr());
        plugin = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    public static DeathRain getPlugin(){
        return plugin;
    }
}
