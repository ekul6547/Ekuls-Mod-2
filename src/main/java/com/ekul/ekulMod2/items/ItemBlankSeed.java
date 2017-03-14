package com.ekul.ekulMod2.items;

import com.ekul.ekulMod2.main.EkulMod;
import com.ekul.ekulMod2.main.ItemModelProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlankSeed extends ItemSeeds implements ItemModelProvider{
    protected String name;
    protected String lore;

    public ItemBlankSeed(String name, Block crop, Block farmOn){
        super(crop, farmOn);
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }
    public ItemBlankSeed(String name, Block crop, Block farmOn, String loreTo){
        this(name, crop, farmOn);
        this.lore = loreTo;
    }

    @Override
    public void registerItemModel(Item item) {
        EkulMod.proxy.registerItemRenderer(item, 0, name);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        if(lore != null && lore != "") {
            par3List.add(lore);
        }
    }
}
