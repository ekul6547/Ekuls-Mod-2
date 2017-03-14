package com.modHelpers;

import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.item.ItemStack;

public class HelperProjectE {
    /**
     * Called anytime after items are declared
     * @author ekul6547
     */

    public static void setEMC(ItemStack stack, int value){
        ProjectEAPI.getEMCProxy().registerCustomEMC(stack, value);
    }

    public static int getEMC(ItemStack stack){
        return ProjectEAPI.getEMCProxy().getValue(stack);
    }

    public static boolean hasEMC(ItemStack stack) {
        return ProjectEAPI.getEMCProxy().hasValue(stack);
    }
}
