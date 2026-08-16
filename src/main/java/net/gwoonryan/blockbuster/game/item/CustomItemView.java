package net.gwoonryan.blockbuster.game.item;

import lombok.Getter;
import net.gwoonryan.blockbuster.game.item.components.ItemComponent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public final class CustomItemView {

    @Getter
    private final ItemStack stack;
    @Getter
    private final ItemDefinition definition;

    public CustomItemView(ItemStack stack, ItemDefinition definition) {
        this.stack = stack;
        this.definition = definition;
    }

    public <T extends ItemComponent> Optional<T> component(Class<T> type) {
        return definition.component(type);
    }
}
