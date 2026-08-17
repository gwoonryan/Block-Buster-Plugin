package net.gwoonryan.blockbuster.game.item.components;

import net.gwoonryan.blockbuster.game.item.LoreSection;
import net.gwoonryan.blockbuster.game.item.context.LoreContext;
import net.kyori.adventure.text.Component;

import java.util.List;

public record MeleeWeaponComponent(
        double damage,
        double attackSpeed
) implements ItemComponent, LoreComponent {

    @Override
    public LoreSection loreSection() {
        return LoreSection.DAMAGE;
    }

    @Override
    public List<Component> getLore(LoreContext context) {
        return List.of(
                Component.text("§7Damage: §c" + damage),
                Component.text("§7Attack Speed: §f" + attackSpeed)
        );
    }
}
