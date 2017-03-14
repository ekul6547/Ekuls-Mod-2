/*package com.ekul.ekulMod2.items;

import com.modHelpers.HelperTESLA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Loader;

import java.util.List;

public class ItemBattery extends Item{
    public String name;
    boolean powered = Loader.isModLoaded("tesla");

    public ItemBattery(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    @Override
    public void registerItemModel(Item item) {
        ModClass.proxy.registerItemRenderer(item, 0, name);
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if(powered) {
            new HelperTESLA.displayInfo(stack, player, tooltip, advanced);
        }
    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, NBTTagCompound nbt) {
        if(powered) {
            return new HelperTESLA.powerProvider(stack, 20000, 200, 200);
        }else{
            return null;
        }
    }
}*/
