package net.gwoonryan.blockbuster.game.item.api.components;

import net.gwoonryan.blockbuster.game.item.api.LoreSection;
import net.gwoonryan.blockbuster.game.item.api.context.LoreContext;
import net.kyori.adventure.text.Component;

import java.util.List;

public record MeleeWeaponComponent(
        double damage,
        double attackSpeed
) implements net.gwoonryan.blockbuster.game.item.api.components.ItemComponent, net.gwoonryan.blockbuster.game.item.api.components.LoreComponent {

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
