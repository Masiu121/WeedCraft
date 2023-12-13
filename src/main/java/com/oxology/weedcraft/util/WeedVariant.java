package com.oxology.weedcraft.util;

import net.minecraft.util.IStringSerializable;

import java.util.Random;

public enum WeedVariant implements IStringSerializable {
    NONE("none", 0.0f),
    PURPLE_HAZE("purple_haze", 0.3f),
    LEMON_HAZE("lemon_haze", 0.5f),
    AMNESIA_HAZE("amnesia_haze", 0.2f);

    private final String name;
    private final float chance;

    WeedVariant(String name, float chance) {
        this.name = name;
        this.chance = chance;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public float getChance() {
        return chance;
    }

    public static WeedVariant getRandom(Random random) {
        float sum = 0;
        for(WeedVariant weedVariant : values()) {
            sum += weedVariant.chance;;
        }

        float selected = random.nextFloat();
        float selectedSum = 0;
        for(WeedVariant weedVariant : values()) {
            selectedSum += weedVariant.chance;
            if(selected < selectedSum) return weedVariant;
        }

        return NONE;
    }
}
