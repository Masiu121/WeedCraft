package com.oxology.weedcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.Level;

import java.util.Random;

public class WeedPotBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    private static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);

    public WeedPotBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader block, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(AGE) < 7;
    }

    public void randomTick(BlockState pState, ServerWorld pWorld, BlockPos pPos, Random pRandom) {
        if (!pWorld.isAreaLoaded(pPos, 1)) return;
        if (pWorld.getRawBrightness(pPos, 0) >= 9) {
            int currentAge = pState.getValue(AGE);
            boolean grow = pRandom.nextInt((int)(25.0F / 4) + 1) == 0;
            if(grow) pWorld.setBlock(pPos, pState.setValue(AGE, currentAge+1), 2);
        }

        LOGGER.log(Level.INFO, "sdasdfa");
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
