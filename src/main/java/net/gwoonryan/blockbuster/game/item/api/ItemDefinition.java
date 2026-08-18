package net.gwoonryan.blockbuster.game.item.api;

import lombok.Getter;
import net.gwoonryan.blockbuster.game.componentObjects.ComponentObjectDefinition;
import net.gwoonryan.blockbuster.game.item.api.components.ItemComponent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.List;

public final class ItemDefinition extends ComponentObjectDefinition {

    @Getter
    private final NamespacedKey id;
    @Getter
    private final Material material;
    @Getter
    private final String displayName;
    private final List<ItemComponent> components;

    public ItemDefinition(NamespacedKey id, Material material, String displayName, List<ItemComponent> components) {
        super(components);
        this.id = id;
        this.material = material;
        this.displayName = displayName;
        this.components = components;
    }
}
