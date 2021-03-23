package eu.endermite.balancebringer;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.ArrayList;
import java.util.List;

public class ConfigCache {

    FileConfiguration config;
    BalanceBringer plugin;
    public boolean debug, villager_prevent_spawn, villager_actively_remove, mending_actively_remove,
            remove_anvil_repair_limit;
    public int anvil_repair_cost;
    public List<World> villagerWorldBlacklist = new ArrayList<>();

    public ConfigCache() {
        plugin = BalanceBringer.plugin;
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();

        debug = config.getBoolean("debug", false);

        villager_prevent_spawn = getBoolean("villagers.deny-spawn", false);
        villager_actively_remove = getBoolean("villagers.actively-remove", false);
        mending_actively_remove = getBoolean("mending.actively-remove", false);
        remove_anvil_repair_limit = getBoolean("mending.remove-anvil-repair-limit.enable", false);
        anvil_repair_cost = getInt("mending.remove-anvil-repair-limit.max-cost", 35);

        // If none of the villager features are on don't populate the ignored worlds list
        if (villager_prevent_spawn || villager_actively_remove) {
            for (String w : getStringList("villagers.ignored-worlds", new ArrayList<>())) {
                World world = Bukkit.getWorld(w);
                if (world != null)
                    villagerWorldBlacklist.add(world);
            }
        }

    }

    private boolean getBoolean(String path, boolean fallback) {
        config.addDefault(path, fallback);
        plugin.saveConfig();
        return config.getBoolean(path, fallback);
    }

    private int getInt(String path, int fallback) {
        config.addDefault(path, fallback);
        plugin.saveConfig();
        return config.getInt(path, fallback);
    }

    private List<String> getStringList(String path, List<String> fallback) {
        config.addDefault(path, fallback);
        plugin.saveConfig();
        return config.getStringList(path);
    }

}
