package com.modHelpers;


import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class HelperIC2 {
    /**
     * Needs to be called in Init
     * @author ekul6547
     */

    public static void addMaceratorRecipe(ItemStack input, ItemStack outputA){
        Recipes.macerator.addRecipe(new ic2.api.recipe.RecipeInputItemStack(input,input.stackSize), new NBTTagCompound(), false, outputA);
    }

    public static void addExtractorRecipe(ItemStack input, ItemStack outputA){
        Recipes.extractor.addRecipe(new ic2.api.recipe.RecipeInputItemStack(input,input.stackSize), new NBTTagCompound(), false, outputA);
    }

    public static void addCompressorRecipe(ItemStack input, ItemStack outputA){
        Recipes.compressor.addRecipe(new ic2.api.recipe.RecipeInputItemStack(input,input.stackSize), new NBTTagCompound(), false, outputA);
    }

    public static void addCentrifugeRecipe(ItemStack input, ItemStack outputA){
        Recipes.centrifuge.addRecipe(new ic2.api.recipe.RecipeInputItemStack(input,input.stackSize), new NBTTagCompound(), false, outputA);
    }

    public static void addBlockCutterRecipe(ItemStack input, ItemStack outputA){
        Recipes.blockcutter.addRecipe(new ic2.api.recipe.RecipeInputItemStack(input,input.stackSize), new NBTTagCompound(), false, outputA);
    }

    public static void addBlastFurnaceRecipe(ItemStack input, ItemStack outputA){
        Recipes.blastfurnace.addRecipe(new ic2.api.recipe.RecipeInputItemStack(input,input.stackSize), new NBTTagCompound(), false, outputA);
    }

    public static void addRecyclerRecipe(ItemStack input, ItemStack outputA){
        Recipes.recycler.addRecipe(new ic2.api.recipe.RecipeInputItemStack(input,input.stackSize), new NBTTagCompound(), false, outputA);
    }
}