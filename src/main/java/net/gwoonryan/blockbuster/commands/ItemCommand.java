package net.gwoonryan.blockbuster.commands;

import net.gwoonryan.blockbuster.commands.api.AdminCommand;
import net.gwoonryan.blockbuster.game.item.ItemDefinition;
import net.gwoonryan.blockbuster.game.item.ItemManager;
import net.gwoonryan.blockbuster.game.item.ItemStackFactory;
import net.gwoonryan.blockbuster.game.player.BBPlayer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemCommand extends AdminCommand {

    public ItemCommand(){
        super("item");
    }

    @Override
    public void runCommand(Player player, BBPlayer bp, String[] args) {
        if (args.length == 0){
            return;
        }
        ItemStack item;
        Optional<ItemDefinition> def = ItemManager.getDefinition(args[0]);
        if (def.isEmpty()){
            bp.sendMessage("This item does not exist!");
            return;
        }
        int amount = 1;
        if (args.length > 1){
            try{
                amount = Integer.parseInt(args[1]);
            }catch (NumberFormatException e){
                bp.sendMessage("That is not a number");
                return;
            }
        }
        item = ItemStackFactory.create(def.get(), amount);
        player.getInventory().addItem(item);
    }

    @Override
    public List<String> tabComplete(Player player, BBPlayer bpp, String[] args) {
        List<String> ret = new ArrayList<>();
        if (args.length == 1) {
            for (NamespacedKey s : ItemManager.getRegisteredItems().keySet()) {
                if (s.getKey().toLowerCase().startsWith(args[0].toLowerCase())) {
                    ret.add(s.getKey());
                }
            }
        }else{
            for (int i = 1; i < 65; i++) {
                ret.add(String.valueOf(i));
            }
        }
        return ret;
    }
}
