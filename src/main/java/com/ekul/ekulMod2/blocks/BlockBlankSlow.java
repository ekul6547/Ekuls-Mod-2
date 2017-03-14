package com.ekul.ekulMod2.blocks;

import java.util.Random;

import com.ekul.ekulMod2.main.EkulItems;
import com.ekul.ekulMod2.main.EkulMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockBlankSlow extends BlockBlank {

    protected static final AxisAlignedBB axis = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
    protected static Double slow;

    public BlockBlankSlow(Material mat,String name,Double slowness){
        super(mat,name);
        slow = slowness;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (fortune < 1) {
            return EkulItems.item_runicGem;
        } else {
            return EkulItems.item_runicGem;
        }
    }

    @Override
    public int quantityDropped(Random rand) {
        return 4 + rand.nextInt(4);
    }
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return axis;
    }

    /**
     * Called When an Entity Collided with the Block
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        entityIn.motionX *= slow;
        entityIn.motionZ *= slow;
    }

}
