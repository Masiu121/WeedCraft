package com.oxology.weedcraft.item;

import com.oxology.weedcraft.WeedCraft;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class WeedSeedsItem extends BlockItem {
    public WeedSeedsItem(Block block) {
        super(block, new Properties().tab(WeedCraft.creativeTab));
    }
}
