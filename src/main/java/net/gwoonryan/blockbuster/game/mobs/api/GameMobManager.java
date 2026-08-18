package net.gwoonryan.blockbuster.game.mobs.api;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class GameMobManager {

    private final RunningGame game;
    private final HashMap<UUID, CustomMob> mobs = new HashMap<>();

    public GameMobManager(RunningGame game){

        this.game = game;
    }

    public void removeMob(CustomMob mob){
        mobs.remove(mob.getMobReference());
    }

    public void spawnMob(CustomMob mob, Location spawnLoc){
        World w = spawnLoc.getWorld();
        if (w == null){
            return;
        }
        Entity ent = w.spawnEntity(spawnLoc, mob.getMobType(), false);
        w.spawnParticle(Particle.CLOUD, ent.getLocation(), 10, 0.1f, 0.1f, 0.1f, 0.2f);
        mob.init(ent, this);
        mob.onSpawn(ent);
        mobs.put(ent.getUniqueId(), mob);
    }

    public CustomMob getMob(UUID uuid){
        if (mobs.containsKey(uuid)){
            return mobs.get(uuid);
        }
        return null;
    }

    public void damageEvent(EntityDamageByEntityEvent e){
        if (!(e.getDamager() instanceof Player player)){
            return;
        }
//        e.setCancelled(true);
        e.setDamage(0);
        if (mobs.containsKey(e.getEntity().getUniqueId())){
            double damage = 1;
            ItemStack item = player.getInventory().getItemInMainHand();
            boolean usedWeapon = true;
            if (item.getAmount() == 0 || item.getType() == Material.AIR){
                usedWeapon = false;
            }
            if (usedWeapon) {
                    //damage = item_damage + item_modifier;
            }
            CustomMob mob = mobs.get(e.getEntity().getUniqueId());
            if (mob instanceof DamageChanging dcm){
                damage = dcm.onMobTakeDamage(damage);
            }
            if (damage < 0.01){
                game.getGamePlayer().playSound(Sound.ITEM_SHIELD_BLOCK, 1f, 1f);
            }
            new DisplayDamage(mob, e.getEntity().getLocation(), damage);
            boolean dead = mob.applyDamage(damage);
        }
    }

    private static class DisplayDamage extends BukkitRunnable {

        private final Location entityLoc;
        private final double damage;

        private UUID entityUUID;

        public DisplayDamage(CustomMob mobThatWasDamaged, Location entityLoc, double damage) {
            this.entityLoc = entityLoc;
            this.damage = damage;
            World w = entityLoc.getWorld();
            if (w == null) {
                return;
            }
            TextDisplay display = (TextDisplay) w.spawnEntity(entityLoc.add(Roguelite.rnd.nextDouble(-.5, .5),Roguelite.rnd.nextDouble(1, 2),Roguelite.rnd.nextDouble(0, .5)), EntityType.TEXT_DISPLAY, false);
            display.setInvulnerable(true);
            display.setGravity(false);
            display.setCustomNameVisible(false);
            display.setAlignment(TextDisplay.TextAlignment.CENTER);
            if (mobThatWasDamaged instanceof DamageDisplayChanging displayChanger){
                display.setText(displayChanger.getDisplayText((int) damage));
            }else {
                display.setText("§7" + (int) damage);
            }
            display.setBillboard(Display.Billboard.CENTER);
            entityUUID = display.getUniqueId();
            this.runTaskLater(Roguelite.getPlugin(), 15L);
        }

        @Override
        public void run() {
            Entity ent = Bukkit.getEntity(entityUUID);
            if (ent != null){
                ent.remove();
            }
        }
    }
}
