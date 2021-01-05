package eu.endermite.balancebringer.villagers;

import eu.endermite.balancebringer.BalanceBringer;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class VillagerDenierListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onVillagerSpawn(org.bukkit.event.entity.EntitySpawnEvent event) {
        if (!BalanceBringer.getConfigCache().villager_prevent_spawn)
            return;
        if (!event.getEntityType().equals(EntityType.VILLAGER)
                && !event.getEntityType().equals(EntityType.ZOMBIE_VILLAGER))
            return;
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onChunkLoad(org.bukkit.event.world.ChunkLoadEvent event) {
        if (!BalanceBringer.getConfigCache().villager_actively_remove)
            return;
        Entity[] entities = event.getChunk().getEntities();
        Bukkit.getScheduler().runTaskAsynchronously(BalanceBringer.plugin, () -> {
            for (Entity entity : entities) {
                if (!(entity instanceof Villager) && !(entity instanceof ZombieVillager))
                    continue;
                Bukkit.getScheduler().runTask(BalanceBringer.plugin, entity::remove);
            }
        });
    }

}
