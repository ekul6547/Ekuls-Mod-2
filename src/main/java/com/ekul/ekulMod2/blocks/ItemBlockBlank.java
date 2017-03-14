package com.ekul.ekulMod2.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockBlank extends ItemBlock{

    public ItemBlockBlank(Block block){
        super(block);
        //setUnlocalizedName(block.getUnlocalizedName());
        setRegistryName(block.getRegistryName());
    }
}
