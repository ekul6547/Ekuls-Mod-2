package com.ekul.ekulMod2.blocks;

import com.ekul.ekulMod2.main.EkulMod;
import com.ekul.ekulMod2.main.ItemModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBlank extends Block implements ItemModelProvider{
    protected String name;
    protected int power;

    public BlockBlank(Material material, String name) {
        super(material);
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }
    public BlockBlank(Material material, String name, int powerValue) {
        this(material,name);
        power = powerValue;
    }
    @Override
    public void registerItemModel(Item item) {
        EkulMod.proxy.registerItemRenderer(item, 0, name);
    }

    @Override
    public boolean canProvidePower(IBlockState state)
    {
        return power != 0;
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return power;
    }

}
