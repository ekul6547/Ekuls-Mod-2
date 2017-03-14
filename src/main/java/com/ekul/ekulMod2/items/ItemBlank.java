package com.ekul.ekulMod2.items;

import com.ekul.ekulMod2.main.EkulMod;
import com.ekul.ekulMod2.main.ItemModelProvider;
import moze_intel.projecte.api.item.IItemEmc;
import moze_intel.projecte.api.proxy.IEMCProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemBlank extends Item implements ItemModelProvider{
    protected String name;
    protected String lore;

    public ItemBlank(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }
    public ItemBlank(String name, String loreTo) {
        this(name);
        lore = loreTo;
    }

    @Override
    public void registerItemModel(Item item) {
        EkulMod.proxy.registerItemRenderer(item, 0, name);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced)
    {
        if(lore != null && lore != "") {
            list.add(lore);
        }
    }
}
