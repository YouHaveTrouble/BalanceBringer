package eu.endermite.balancebringer;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigCache {

    FileConfiguration config;
    public boolean villager_prevent_spawn, villager_actively_remove,
            mending_actively_remove, remove_anvil_repair_limit;
    public int anvil_repair_cost;

    public ConfigCache() {
        BalanceBringer plugin = BalanceBringer.plugin;
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
        villager_prevent_spawn = config.getBoolean("villagers.deny-spawn", false);
        villager_actively_remove = config.getBoolean("villagers.actively-remove", false);
        mending_actively_remove = config.getBoolean("mending.actively-remove", false);
        remove_anvil_repair_limit = config.getBoolean("mending.remove-anvil-repair-limit.enable", false);
        anvil_repair_cost = config.getInt("mending.remove-anvil-repair-limit.static-cost", 1);
    }

}
