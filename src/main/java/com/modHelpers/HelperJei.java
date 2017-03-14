package com.modHelpers;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import net.minecraft.item.ItemStack;
import javax.annotation.Nonnull;
import java.util.ArrayList;

/**
 * To add item descriptions to items and blocks in JEI
 * call addJEIDescription() in preInit
 * Not to add new categories or custom recipes (yet!)
 * @author ekul6547
 */

@JEIPlugin
public class HelperJei implements IModPlugin{
    public static class itemDesc {
        public ItemStack itemStack;
        public String[] descriptions;
        public itemDesc(ItemStack stack, String... descs){
            itemStack = stack;
            descriptions = descs;
        }
    }
    public static ArrayList<itemDesc> descReg = new ArrayList<itemDesc>();
    public static IJeiRuntime RUNTIME = null;

    public HelperJei(){System.out.println("HelperJei ACTIVATED!!!");}

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
    }

    public void register(@Nonnull IModRegistry registry) {
        for(itemDesc desc : descReg) {
            registry.addDescription(desc.itemStack, desc.descriptions);
            System.out.println("HelperJei added description for: "+desc.itemStack.getDisplayName());
        }
    }

    /**
     * Call this preInit
     */
    public static void addJEIDescription(ItemStack stack, String... descs){
        descReg.add(new itemDesc(stack,descs));
    }

    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {
        RUNTIME = jeiRuntime;
    }
}