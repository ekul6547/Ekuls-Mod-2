package com.ekul.ekulMod2.main;

import com.modHelpers.HelperTinker;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.tools.TinkerTraits;

public class EkulTinkers {

    public static void LoadTinkers (){
        Fluid ekulFluid = HelperTinker.newFluid("EkuliumF", 0xf9ea95, false, "Ekulium", true);
        Fluid runicFluid = HelperTinker.newFluid("RunicF", 0xf0312f, true, "Runic", true);
        Fluid bedrockFluid = HelperTinker.newFluid("BedrockF", 0x333333, true, "Bedrock", true);
        Fluid mixedFluid = HelperTinker.newFluid("MixedF", 0xa0e6c5, true, "Mixed", false);
        HelperTinker.addFluidAlloy(new FluidStack(mixedFluid,144), new FluidStack(bedrockFluid,144*9), new FluidStack(ekulFluid,144*18), new FluidStack(runicFluid,144*36));

        Material ekulMat = HelperTinker.newMaterial("ekulium",0xf9ea95, "ingotEkulium", false);
        ekulMat = HelperTinker.addRepresentativeItem(ekulMat, new ItemStack(EkulItems.item_ekulium));
        ekulMat = HelperTinker.setCastingFluid(ekulMat, ekulFluid);
        ekulMat = HelperTinker.addGlobalTraits(ekulMat, TinkerTraits.writable, TinkerTraits.dense, TinkerTraits.sharp);
        ekulMat = HelperTinker.addMaterialStats(ekulMat,
                new HeadMaterialStats(2048, 12, 6, 4),
                new HandleMaterialStats(1.5f, 2048),
                new ExtraMaterialStats(128),
                new BowMaterialStats(1.5f, 3.14f, 6f));
        HelperTinker.registerMat(ekulMat);

        Material runicMat = HelperTinker.newMaterial("runic",0xf0312f, "gemRunic", false);
        runicMat = HelperTinker.addRepresentativeItem(runicMat, new ItemStack(EkulItems.item_runicGem));
        runicMat = HelperTinker.setCastingFluid(runicMat, runicFluid);
        runicMat = HelperTinker.addGlobalTraits(runicMat, TinkerTraits.writable, TinkerTraits.dense, TinkerTraits.sharp);
        runicMat = HelperTinker.addMaterialStats(runicMat,
                new HeadMaterialStats(6192, 16, 10, 5),
                new HandleMaterialStats(2.0f, 6192),
                new ExtraMaterialStats(256),
                new BowMaterialStats(0.3f, 7f, 9f));
        HelperTinker.registerMat(runicMat);

        HelperTinker.addBasinRecipe(new ItemStack(EkulBlocks.block_mix), new ItemStack(EkulItems.item_WhixQuartz), mixedFluid, 144, 10, true, true);
        HelperTinker.addMeltingRecipe(new ItemStack(EkulBlocks.block_mix), new FluidStack(mixedFluid,144));
    }

}