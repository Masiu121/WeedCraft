package com.oxology.weedcraft.util;

import net.minecraft.util.IStringSerializable;

public enum WeedVariant implements IStringSerializable {
    NONE("none"),
    PURPLE_HAZE("purple_haze"),
    LEMON_HAZE("lemon_haze");

    private final String name;

    WeedVariant(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
