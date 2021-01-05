package eu.endermite.balancebringer;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigCache {

    FileConfiguration config;
    public boolean villager_prevent_spawn, villager_actively_remove,
            mending_actively_remove;

    public ConfigCache() {
        BalanceBringer plugin = BalanceBringer.plugin;
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
        villager_prevent_spawn = config.getBoolean("villagers.deny-spawn", true);
        villager_actively_remove = config.getBoolean("villagers.actively-remove", true);
        mending_actively_remove = config.getBoolean("mending.actively-remove", true);
    }

}
