package eu.endermite.balancebringer.mending;

import eu.endermite.balancebringer.BalanceBringer;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class MendingDenierListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onMendingItemPickup(EntityPickupItemEvent event) {
        replaceMendingOnItem(event.getItem().getItemStack());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInvOpen(InventoryOpenEvent event) {
        for (ItemStack itemStack : event.getInventory().getContents()) {
            replaceMendingOnItem(itemStack);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onLogin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(BalanceBringer.plugin, () -> {
            if (!event.getPlayer().isOnline()) return;
            for (ItemStack itemStack : event.getPlayer().getInventory()) {
                replaceMendingOnItem(itemStack);
            }
        });
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInvClick(InventoryClickEvent event) {
        replaceMendingOnItem(event.getCurrentItem());
        replaceMendingOnItem(event.getCursor());
    }

    private void replaceMendingOnItem(ItemStack itemStack) {
        if (itemStack == null) return;
        if (itemStack.containsEnchantment(Enchantment.MENDING)) {
            itemStack.removeEnchantment(Enchantment.MENDING);
            if (itemStack.containsEnchantment(Enchantment.DURABILITY))
                itemStack.removeEnchantment(Enchantment.DURABILITY);
            itemStack.addEnchantment(Enchantment.DURABILITY, 3);
        }
    }

}
