package eu.endermite.balancebringer.villagers;

import com.destroystokyo.paper.event.entity.EntityAddToWorldEvent;
import eu.endermite.balancebringer.BalanceBringer;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class VillagerActiveRemoverListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onVillagerJoinWorld(EntityAddToWorldEvent event) {
        if (BalanceBringer.getConfigCache().villagerWorldBlacklist.contains(event.getEntity().getWorld().getUID())) return;
        EntityType entity = event.getEntityType();
        if (entity == EntityType.VILLAGER || entity == EntityType.ZOMBIE_VILLAGER)
            event.getEntity().remove();
    }

}
