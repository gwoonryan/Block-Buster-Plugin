package net.gwoonryan.blockbuster.game.item.api;

import lombok.Getter;
import net.gwoonryan.blockbuster.game.item.api.components.ItemComponent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
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

    public <T extends ItemComponent> Optional<T> firstComponentOfType(Class<T> type) {
        return definition.firstComponentOfType(type);
    }

    public <T extends ItemComponent> List<T> allComponentsOfType(Class<T> type) {
        return definition.allComponentsOfType(type);
    }
}
