package com.oxology.weedcraft.item;

import com.oxology.weedcraft.WeedCraft;
import com.oxology.weedcraft.util.WeedVariant;
import net.minecraft.item.Item;

public class WeedSeedsItem extends Item {
    private final WeedVariant weedVariant;

    public WeedSeedsItem(WeedVariant weedVariant) {
        super(new Properties().tab(WeedCraft.creativeTab));
        this.weedVariant = weedVariant;
    }

    public WeedVariant getWeedVariant() {
        return weedVariant;
    }
}
