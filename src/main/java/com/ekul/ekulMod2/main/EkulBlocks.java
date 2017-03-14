package com.ekul.ekulMod2.main;

import com.ekul.ekulMod2.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EkulBlocks {

    //Blocks
    public static Block block_ekuliumOre;
    public static Block block_netherEkuliumOre;
    public static Block block_ekuliumBlock;
    public static Block block_runicOre;
    public static Block block_netherRunicOre;
    public static Block block_runicBlock;
    public static Block block_mix;
    public static BlockWhixCrop block_whixCrop;
    public static void initBlocks(){

        block_ekuliumOre = 		    registerBlock(new BlockBlank(Material.ROCK,"blockEkuliumOre").setCreativeTab(EkulMod.tabEkulMod));
        block_ekuliumOre.setHardness(5.0F).setResistance(10.0F).setLightLevel(5.0F).setHarvestLevel("pickaxe",2);
        block_netherEkuliumOre = 	registerBlock(new BlockBlank(Material.ROCK,"blockNetherEkuliumOre").setCreativeTab(EkulMod.tabEkulMod));
        block_netherEkuliumOre.setHardness(3.0F).setResistance(8.0F).setLightLevel(5.0F).setHarvestLevel("pickaxe",2);
        block_ekuliumBlock = 		registerBlock(new BlockBlank(Material.IRON,"blockEkuliumBlock").setCreativeTab(EkulMod.tabEkulMod));
        block_ekuliumBlock.setHardness(10.0F).setResistance(20.0F).setLightLevel(7.0F).setHarvestLevel("pickaxe", 2);

        block_runicOre = 			registerBlock(new BlockBlankFalling(Material.SAND,"blockRunicOre").setCreativeTab(EkulMod.tabEkulMod));
        block_runicOre.setHardness(15.0F).setResistance(10.0F).setHarvestLevel("shovel", 4);
        block_netherRunicOre = 	    registerBlock(new BlockBlankSlow(Material.SAND,"blockNetherRunicOre",0.2D).setCreativeTab(EkulMod.tabEkulMod));
        block_netherRunicOre.setHardness(13.0F).setResistance(8.0F).setHarvestLevel("shovel", 4);
        block_runicBlock = 		    registerBlock(new BlockBlankSlow(Material.IRON,"blockRunicBlock",0.1D).setCreativeTab(EkulMod.tabEkulMod));
        block_runicBlock.setHardness(50.0F).setResistance(40.0F).setHarvestLevel("pickaxe", 4);

        block_mix = 		        registerBlock(new BlockBlank(Material.IRON,"blockMix",15).setCreativeTab(EkulMod.tabEkulMod));
        block_mix.setHardness(120.0F).setResistance(100.0F).setHarvestLevel("pickaxe", 5);

        block_whixCrop =            registerBlock(new BlockWhixCrop("cropWhix"),null);
    }

    public static <T extends Block> T registerBlock(T block, ItemBlock itemBlock){
        GameRegistry.register(block);
        if(itemBlock != null) {
            GameRegistry.register(itemBlock);
            ((ItemModelProvider) block).registerItemModel(itemBlock);
        }
        return block;
    }
    public static <T extends Block> T registerBlock(T block){
        ItemBlock itemBlock = new ItemBlockBlank(block);
        //itemBlock.setRegistryName(block.getRegistryName());
        return registerBlock(block, itemBlock);
    }
}
