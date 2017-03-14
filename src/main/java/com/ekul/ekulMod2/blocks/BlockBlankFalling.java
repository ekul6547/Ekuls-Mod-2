package com.ekul.ekulMod2.blocks;

import com.ekul.ekulMod2.main.EkulItems;
import com.ekul.ekulMod2.main.EkulMod;
import com.ekul.ekulMod2.main.ItemModelProvider;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockBlankFalling extends BlockFalling implements ItemModelProvider{
    protected String name;

    public BlockBlankFalling(Material mat, String name) {
        super(mat);
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }
    @Override
    public void registerItemModel(Item item) {
        EkulMod.proxy.registerItemRenderer(item, 0, name);
    }

    //@Override
    public Item getItemDropped(IBlockState metadata, Random rand, int fortune) {

        if (fortune < 1) {
            return EkulItems.item_runicGem;
        } else {
            return EkulItems.item_runicGem;
        }
    }

    @Override
    public int quantityDropped(Random rand) {
        return 2 + rand.nextInt(2);
    }

}
