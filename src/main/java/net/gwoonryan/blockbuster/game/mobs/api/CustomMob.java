package net.gwoonryan.blockbuster.game.mobs.api;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public abstract class CustomMob {

    private int maxHp;
    private double hp;
    private int level;
    @Getter
    private final EntityType mobType;
    private GameMobManager manager;
    @Getter
    private UUID mobReference;
    @Getter
    private String name;

    public CustomMob(int maxHp, int level, String name, EntityType mobType) {
        this.level = level;
        this.maxHp = maxHp;
        this.name = name;
        this.hp = maxHp;
        this.mobType = mobType;
    }

    public void init(Entity reference, GameMobManager manager){
        this.mobReference = reference.getUniqueId();
        this.manager = manager;
        updateName();
    }

    // true if killed, false if still alive
    public boolean applyDamage(double amount){
        Entity ent = Bukkit.getEntity(mobReference);
        if (ent == null) {
            manager.removeMob(this);
            return false;
        }
        if (amount < 0){
            amount = 0;
        }
        this.hp = this.hp - amount;
        if (this.hp <= 0) {
            Location loc = ent.getLocation();
            World w = loc.getWorld();
            if (w != null) {
                List<ItemStack> loot = getLoot();
                if (loot != null) {
                    for (ItemStack item : loot) {
                        w.dropItemNaturally(loc.clone().add(0, 0.5, 0), item);
                    }
                }
            }
            if (ent instanceof LivingEntity){
                LivingEntity lv = (LivingEntity) ent;
                lv.damage(100_000_000);
            }else{
                ent.remove();
            }
            this.hp = 0;
            updateName();
            return true;
        }
        updateName();
        return false;
    }

    public void updateName(){
        Entity ent = Bukkit.getEntity(mobReference);
        if (ent == null){
            //call hier ook dat de entity niet meer bestaat
            manager.removeMob(this);
            return;
        }
        String title = "§7Lv." + this.level +  " §a" + this.name +  " §c♥" + ((int) this.hp) + "§f/§c" + this.maxHp;
        ent.setCustomName(title);
        ent.setCustomNameVisible(true);
    }

    public abstract void onSpawn(Entity ent);

    public abstract List<ItemStack> getLoot();
}