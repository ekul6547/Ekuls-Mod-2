package com.ekul.ekulMod2.main;

import com.modHelpers.HelperIC2;
import com.modHelpers.HelperJei;
import com.modHelpers.HelperProjectE;
import com.modHelpers.HelperTinker;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class EkulModIntegration {

    public static void LoadEMC(){
        if(Loader.isModLoaded("ProjectE")){
            try {
                HelperProjectE.setEMC(new ItemStack(Blocks.BEDROCK), 1048576);
                HelperProjectE.setEMC(new ItemStack(EkulItems.item_ekulium), 256);
                HelperProjectE.setEMC(new ItemStack(EkulItems.item_runicGem), 1024);
                HelperProjectE.setEMC(new ItemStack(EkulItems.item_WhixSeed), 512);
                HelperProjectE.setEMC(new ItemStack(EkulItems.item_MonoWhix), 512);
                HelperProjectE.setEMC(new ItemStack(EkulItems.item_tingotDust), 2080);
            }catch(java.lang.NoClassDefFoundError Nclass){
                System.out.println("EKUL:PROJECTE:"+Nclass);
            }
        }
    }

    public static void specialEMC(){
        if(Loader.isModLoaded("ProjectE")){
            try{
                if(Loader.isModLoaded("tconstruct")){
                    try{
                        int toEMC = (4096*4) + (2304*2) + 1048576 + 2098176;
                        HelperProjectE.setEMC(new ItemStack(EkulBlocks.block_mix), toEMC);
                    }catch(java.lang.NoClassDefFoundError Nclass){
                        System.out.println("EKUL:TINKER:"+Nclass);
                    }
                }
            }catch(java.lang.NoClassDefFoundError Nclass){
                System.out.println("EKUL:PROJECTE:"+Nclass);
            }
        }
    }

    public static void LoadIC2(){
        if(Loader.isModLoaded("IC2")){
            try {
                HelperIC2.addMaceratorRecipe(new ItemStack(EkulItems.item_tingot,1),new ItemStack(EkulItems.item_tingotDust,1));
                HelperIC2.addMaceratorRecipe(new ItemStack(EkulItems.item_tiot,1),new ItemStack(EkulItems.item_tiotDust,1));
            }catch(java.lang.NoClassDefFoundError Nclass){
                System.out.println("EKUL:IC2:"+Nclass);
            }
        }
    }

    public static void LoadJEI(){
        if(Loader.isModLoaded("JEI")){
            try{
                HelperJei.addJEIDescription(new ItemStack(Blocks.BEDROCK),"Bedrock can be harvested using the Deletion Staff or Deletion Staff Core.");
                HelperJei.addJEIDescription(new ItemStack(EkulItems.tool_delStaffCore),"Core component for the Deletion staff.","Can harvest practically anything.");
                HelperJei.addJEIDescription(new ItemStack(EkulItems.tool_delStaff),"Can harvest practically anything on use.","Sneak while using to harvest a vein of the same block.","Can also instantly kill many entities, so be careful.");
                if(!Loader.isModLoaded("tesla")) {
                    HelperJei.addJEIDescription(new ItemStack(EkulItems.tool_delStaff), "Sneak use while not clicking on anything to repair at the cost of Runic Gems or health.");
                }

                String[] helm = {"Night vision","Saturation","Water breathing","View mine",""};
                if(!Loader.isModLoaded("tesla")){helm[helm.length-1] = "Auto repair";}
                String[] chest = {"Constant health increase","Strength","Haste","Anti fire",""};
                if(!Loader.isModLoaded("tesla")){chest[chest.length-1] = "Auto repair";}
                String[] legs = {"Jump boost","Speed",""};
                if(!Loader.isModLoaded("tesla")){legs[legs.length-1] = "Auto repair";}
                String[] boots = {"No Fall Damage",""};
                if(!Loader.isModLoaded("tesla")){boots[boots.length-1] = "Auto repair";}
                HelperJei.addJEIDescription(new ItemStack(EkulItems.armor_runicHelm),helm);
                HelperJei.addJEIDescription(new ItemStack(EkulItems.armor_runicHelm),"View mine info:","The View mine works by utilising the OreDictionary. If the block you're looking at is designated as an ore, the helmet will give you the equivalent into gem, ingot or dust, and turn the block to stone.");
                HelperJei.addJEIDescription(new ItemStack(EkulItems.armor_runicChest), chest);
                HelperJei.addJEIDescription(new ItemStack(EkulItems.armor_runicLegs), legs);
                HelperJei.addJEIDescription(new ItemStack(EkulItems.armor_runicBoots),boots);

                HelperJei.addJEIDescription(new ItemStack(EkulItems.item_WhixSeed), "Found in loot chests.","Curiously, also rarely dropped by light blue sheep. No idea why.");

            }catch(java.lang.NullPointerException nullus){
                System.out.println("EKUL:JEI:"+nullus);
            }
        }
    }

    public static void LoadTinker(){
        if(Loader.isModLoaded("tconstruct")){
            try{
                EkulTinkers.LoadTinkers();
            }catch(java.lang.NoClassDefFoundError Nclass){
                System.out.println("EKUL:TINKER:"+Nclass);
            }
        }
    }
}
