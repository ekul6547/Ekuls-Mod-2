package com.ekul.ekulMod2.blocks;

import com.ekul.ekulMod2.main.EkulItems;
import net.minecraft.item.Item;

public class BlockWhixCrop extends BlockBlankCrop{

    public BlockWhixCrop(String name){
        super(name);
    }
    @Override
    protected Item getSeed(){
        return EkulItems.item_WhixSeed;
    }

    @Override
    protected Item getCrop(){
        return  EkulItems.item_MonoWhix;
    }
}
