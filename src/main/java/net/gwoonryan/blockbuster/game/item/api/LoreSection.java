package net.gwoonryan.blockbuster.game.item.api;

public enum LoreSection {

    DAMAGE(100),
    STATS(200),
    ABILITIES(300),
    RUNES(400),
    FLAVOR(500);

    private final int order;

    LoreSection(int order) {
        this.order = order;
    }

    public int order() {
        return order;
    }
}
