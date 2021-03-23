package eu.endermite.balancebringer.mending;

import eu.endermite.balancebringer.BalanceBringer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class ItemFixListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onItemFix(org.bukkit.event.inventory.PrepareAnvilEvent event) {

        if (!BalanceBringer.getConfigCache().remove_anvil_repair_limit)
            return;

        AnvilInventory anvilInventory = event.getInventory();
        ItemStack item = anvilInventory.getFirstItem();
        if (item == null)
            return;
        if (!(item.getItemMeta() instanceof Damageable))
            return;

        int maxCost = BalanceBringer.getConfigCache().anvil_repair_cost;

        if (anvilInventory.getRepairCost() >= maxCost) {
            item.setRepairCost(maxCost);
            anvilInventory.setMaximumRepairCost(39);
        }


    }

}
