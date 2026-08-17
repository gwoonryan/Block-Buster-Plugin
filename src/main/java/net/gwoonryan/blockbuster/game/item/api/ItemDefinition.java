package net.gwoonryan.blockbuster.game.item.api;

import lombok.Getter;
import net.gwoonryan.blockbuster.game.item.api.components.ItemComponent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.List;
import java.util.Optional;

public final class ItemDefinition {

    @Getter
    private final NamespacedKey id;
    @Getter
    private final Material material;
    @Getter
    private final String displayName;
    private final List<ItemComponent> components;

    public ItemDefinition(NamespacedKey id, Material material, String displayName, List<ItemComponent> components) {
        this.id = id;
        this.material = material;
        this.components = components;
        this.displayName = displayName;
    }

    public <T extends ItemComponent> Optional<T> firstComponentOfType(Class<T> type) {
        return components.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .findFirst();
    }

    public <T extends ItemComponent> List<T> allComponentsOfType(Class<T> type) {
        return components.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .toList();
    }
}
