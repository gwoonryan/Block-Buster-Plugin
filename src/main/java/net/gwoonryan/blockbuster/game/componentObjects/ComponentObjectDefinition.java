package net.gwoonryan.blockbuster.game.componentObjects;

import java.util.List;
import java.util.Optional;

public class ComponentObjectDefinition<TypedComponent extends GeneralComponent> {
    private final List<TypedComponent> components;

    public ComponentObjectDefinition(List<TypedComponent> components)
    {
        this.components = components;
    }

    public <T extends GeneralComponent> Optional<T> firstComponentOfType(Class<T> type) {
        return components.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .findFirst();
    }

    public <T extends GeneralComponent> List<T> allComponentsOfType(Class<T> type) {
        return components.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .toList();
    }
}
