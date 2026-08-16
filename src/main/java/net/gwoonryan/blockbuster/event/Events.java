package net.gwoonryan.blockbuster.event;

import net.gwoonryan.blockbuster.game.item.CustomItemView;
import net.gwoonryan.blockbuster.game.item.ItemManager;
import net.gwoonryan.blockbuster.game.item.components.UseComponent;
import net.gwoonryan.blockbuster.game.item.context.ItemUseContext;
import net.gwoonryan.blockbuster.game.player.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Events implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if (event.getHand() != EquipmentSlot.HAND)
            return;

        CustomItemView item = ItemManager.resolve(event.getItem()).orElse(null);

        if (item == null)
            return;

        item.component(UseComponent.class)
                .ifPresent(component -> component.onUse(
                        new ItemUseContext(
                                PlayerManager.getPlayer(event.getPlayer()),
                                item,
                                event.getAction()
                        )
                ));
    }
}
