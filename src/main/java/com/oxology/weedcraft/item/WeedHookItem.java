package com.oxology.weedcraft.item;

import com.oxology.weedcraft.WeedCraft;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class WeedHookItem extends BlockItem {
    public WeedHookItem(Block block) {
        super(block, new Properties().tab(WeedCraft.creativeTab));
    }
}
