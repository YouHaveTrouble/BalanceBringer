package eu.endermite.balancebringer.mending;

import eu.endermite.balancebringer.BalanceBringer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ItemFixListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onItemFix(org.bukkit.event.inventory.PrepareAnvilEvent event) {

        if (!BalanceBringer.getConfigCache().remove_anvil_repair_limit)
            return;

        ItemStack result = event.getInventory().getResult();
        if (result == null)
            return;
        result.setRepairCost(BalanceBringer.getConfigCache().anvil_repair_cost);
        event.setResult(result);

    }

}
