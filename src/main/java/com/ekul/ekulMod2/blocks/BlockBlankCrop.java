package com.ekul.ekulMod2.blocks;

import com.ekul.ekulMod2.items.ItemBlankSeed;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BlockBlankCrop extends BlockCrops {
    protected String name;
    protected ItemBlankSeed seedItem;
    protected Item cropItem;

    public BlockBlankCrop(String name){
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    @Override
    protected Item getSeed(){
        return seedItem;
    }

    @Override
    protected Item getCrop(){
        return  cropItem;
    }
}
