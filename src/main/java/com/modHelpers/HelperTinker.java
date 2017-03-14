package com.modHelpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.TinkerIntegration;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.tools.TinkerMaterials;

import javax.annotation.Nullable;

public class HelperTinker {

    /**
     * Call this first to get main material
     * @param identifier To Identify by
     * @param color Color (0xffffff = white)
     * @param oreDictIngot Ore Dictionary Value for an item
     * @param craftable Can be craftable, normal patterns not casts
     */
    public static Material newMaterial(String identifier, int color, String oreDictIngot, boolean craftable){
        Material mat = new Material(identifier, color);
        TinkerMaterials.materials.add(mat);
        mat.setCraftable(craftable);
        mat.addItemIngot(oreDictIngot);
        return mat;
    }

    /**
     * Call this last to register your mat
     * @param mat
     */
    public static void registerMat(Material mat){
        TinkerIntegration.integrationList.add(new MaterialIntegration(mat));
    }

    public static Material setCastingFluid(Material mat, Fluid fluid){
        mat.setFluid(fluid);
        mat.setCastable(true);
        return mat;
    }

    public static void addBasinRecipe(ItemStack output, Fluid fluid, int amount, int time){
        TinkerRegistry.registerBasinCasting(new CastingRecipe(output,null,fluid,amount,time));
    }
    public static void addBasinRecipe(ItemStack output, @Nullable ItemStack cast, Fluid fluid, int amount, int time, boolean consumeCast, boolean switchOutput){
        TinkerRegistry.registerBasinCasting(new CastingRecipe(output, RecipeMatch.ofNBT(cast), new FluidStack(fluid, amount), consumeCast, switchOutput));
    }

    public static void addTableRecipe(ItemStack output, Fluid fluid, int amount, int time){
        TinkerRegistry.registerBasinCasting(new CastingRecipe(output,null,fluid,amount,time));
    }
    public static void addTableRecipe(ItemStack output, @Nullable ItemStack cast, Fluid fluid, int amount, int time, boolean consumeCast, boolean switchOut){
        TinkerRegistry.registerBasinCasting(new CastingRecipe(output,RecipeMatch.ofNBT(cast),new FluidStack(fluid,amount),time,consumeCast,switchOut));
    }

    public static void addMeltingRecipe(ItemStack stack, FluidStack fluidStack){
        TinkerRegistry.registerMelting(stack,fluidStack.getFluid(),fluidStack.amount);
    }
    public static void addMeltingRecipe(ItemStack stack, Fluid fluid, int amount){
        TinkerRegistry.registerMelting(stack,fluid,amount);
    }
    public static void addOreDictMeltingRecipe(String ore, Fluid fluid, int amount){
        TinkerRegistry.registerMelting(ore,fluid,amount);
    }

    public static void addCastForItem(Item item){
        TinkerRegistry.addCastForItem(item);
    }

    public static void addSmelteryFuel(FluidStack stack, int duration){
        TinkerRegistry.registerSmelteryFuel(stack, duration);
    }

    /**
     *
     * @param mat
     * @param representativeItem
     * @return
     */
    public static Material addRepresentativeItem(Material mat, ItemStack representativeItem){
        mat.setRepresentativeItem(representativeItem);
        return mat;
    }

    public static Material addMaterialStats(Material mat, AbstractMaterialStats... stats){
        for(AbstractMaterialStats s : stats){
            TinkerRegistry.addMaterialStats(mat,s);
        }
        return mat;
    }

    public static Material addGlobalTraits(Material mat, ITrait... traits){
        for(ITrait t : traits) {
            mat.addTrait(t);
        }
        return mat;
    }

    public static Material addSpecificTraits(Material mat, String dependancy, ITrait... traits){
        for(ITrait t : traits) {
            mat.addTrait(t,dependancy);
        }
        return mat;
    }

    //IMC Integration

    public static Fluid newFluid(String fluidName, int Color, boolean useLavaMaterial, String oreDictSuffix, boolean newToolForge){
        return newFluid(fluidName, Color, useLavaMaterial, oreDictSuffix, newToolForge, new ResourceLocation("tconstruct:blocks/fluids/molten_metal"), new ResourceLocation("tconstruct:blocks/fluids/molten_metal_flow"));
    }

    /**
     * Create a new fluid for smeltery, and config for it.
     * @param fluidName Name of the fluid
     * @param useLavaMaterial Fluid has Lava physics instead of Water
     * @param oreDictSuffix Suffix for the appropriate ore dictionary (ingotFoo, blockFoo, oreFoo, dustFoo...)
     * @param newToolForge Can blockFoo be used to create a ToolForge
     * @param fluidStill Custom fluid texture for Still (optional)
     * @param fluidFlow Custom fluid texture for flowing (optional)
     */
    public static Fluid newFluid(String fluidName, final int Color, boolean useLavaMaterial, String oreDictSuffix, boolean newToolForge, ResourceLocation fluidStill, ResourceLocation fluidFlow){
        // create fluid.
        // You don't need to add textures for the fluid, just create a Fluid Class that overwrites getColor
        // and pass the following as still and flowing ResourceLocation:
        // still:  "tconstruct:blocks/fluids/molten_metal"
        // flowing: "tconstruct:blocks/fluids/molten_metal_flow"
        Fluid myFluid = new Fluid(fluidName, fluidStill, fluidFlow){
            @Override
            public int getColor() {
                return Color;
            }
        };
        FluidRegistry.registerFluid(myFluid); // fluid has to be registered
        FluidRegistry.addBucketForFluid(myFluid); // add a bucket for the fluid

        // add block for fluid (if desired)
        if(useLavaMaterial){
            Block fluidBlock = new BlockFluidClassic(myFluid, net.minecraft.block.material.Material.LAVA);
        }else {
            Block fluidBlock = new BlockFluidClassic(myFluid, net.minecraft.block.material.Material.WATER);
        }
        // <register block regularly>

        // create NBT for the IMC
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("fluid", myFluid.getName()); // name of the fluid
        tag.setString("ore", oreDictSuffix); // ore-suffix: ingotFoo, blockFoo, oreFoo,...
        tag.setBoolean("toolforge", newToolForge); // if set to true, blockFoo can be used to build a toolforge
        //tag.setTag("alloy", alloysTagList); // you can also send an alloy with the registration (see below)

        // send the NBT to TCon
        FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tag);

        return myFluid;
    }

    /**
     * Allow two or more fluids to mix/alloy together in the smeltery to form a new one.
     * @param fluidOutput FluidStack output - 144 = one ingot, amount here is produced from alloy
     * @param fluidInputs FluidStack[] inputs - 144 = one ingot, so advisable to add them to a multiple of 144
     */
    public static void addFluidAlloy(FluidStack fluidOutput, FluidStack... fluidInputs){
        NBTTagList tagList = new NBTTagList();
        // if you have access to the fluid-instance you can also do FluidStack.writeToNBT
        tagList.appendTag(fluidOutput.writeToNBT(new NBTTagCompound()));
        // first alloy fluid
        for(FluidStack input : fluidInputs) {
            tagList.appendTag(input.writeToNBT(new NBTTagCompound()));
        }

        NBTTagCompound message = new NBTTagCompound();
        message.setTag("alloy", tagList);
        FMLInterModComms.sendMessage("tconstruct", "alloy", message);
    }

    /**
     * Blacklist an item from working in the smeltery
     * Won't work with general ores, blocks, ingots etc.. from ore dictionary
     * Tinkers allows you to smelt items that use X in their crafting, like armor, and this blocks those
     */
    public static void blacklistSmelt(ItemStack stack){
        FMLInterModComms.sendMessage("tconstruct", "blacklistMelting", stack);
    }

    public static void blacklistSmelt(String name){
        FMLInterModComms.sendMessage("tconstruct", "blacklistMelting", name);
    }

    public static void addDryingRackRecipe(ItemStack input, ItemStack output, int timeSeconds){
        // Adding it via Itemstack: Turns vanilla gold ore into a gold block in 3 minutes
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setTag("input", input.writeToNBT(new NBTTagCompound()));
        tagCompound.setTag("output", output.writeToNBT(new NBTTagCompound()));
        tagCompound.setInteger("time", timeSeconds);
        FMLInterModComms.sendMessage(Util.MODID, "addDryingRecipe", tagCompound);
    }
    public static void addDryingRackRecipe(String input, ItemStack output, int timeSeconds){
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setString("input", input);
        tagCompound.setTag("output", output.writeToNBT(new NBTTagCompound()));
        tagCompound.setInteger("time", timeSeconds);
        FMLInterModComms.sendMessage(Util.MODID, "addDryingRecipe", tagCompound);
    }
    public static void addDryingRackRecipe(ItemStack input, String output, int timeSeconds){
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setTag("input", input.writeToNBT(new NBTTagCompound()));
        tagCompound.setString("output", output);
        tagCompound.setInteger("time", timeSeconds);
        FMLInterModComms.sendMessage(Util.MODID, "addDryingRecipe", tagCompound);
    }
    public static void addDryingRackRecipe(String input, String output, int timeSeconds){
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setString("input", input);
        tagCompound.setString("output", output);
        tagCompound.setInteger("time", timeSeconds);
        FMLInterModComms.sendMessage(Util.MODID, "addDryingRecipe", tagCompound);
    }
}
