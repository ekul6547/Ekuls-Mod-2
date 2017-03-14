package com.ekul.ekulMod2.main;


import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class EkulModRecipes {

	public static void OreDic() {
		OreDictionary.registerOre("oreEkulium", new ItemStack(EkulBlocks.block_ekuliumOre));
		OreDictionary.registerOre("oreNetherEkulium", new ItemStack(EkulBlocks.block_netherEkuliumOre));
		OreDictionary.registerOre("oreRunic", new ItemStack(EkulBlocks.block_runicOre));
		OreDictionary.registerOre("oreNetherRunic", new ItemStack(EkulBlocks.block_netherRunicOre));

		OreDictionary.registerOre("ingotEkulium", new ItemStack(EkulItems.item_ekulium));
		OreDictionary.registerOre("ingotNetherEkulium", new ItemStack(EkulItems.item_ekulium));
		OreDictionary.registerOre("gemRunic", new ItemStack(EkulItems.item_runicGem));
		OreDictionary.registerOre("gemNetherRunic", new ItemStack(EkulItems.item_runicGem));
		OreDictionary.registerOre("ingotRunic", new ItemStack(EkulItems.item_runicGem));
		OreDictionary.registerOre("ingotNetherRunic", new ItemStack(EkulItems.item_runicGem));

		OreDictionary.registerOre("blockEkulium", EkulBlocks.block_ekuliumBlock);
		OreDictionary.registerOre("blockRunic", EkulBlocks.block_runicBlock);
		OreDictionary.registerOre("ingotBedrock", EkulItems.item_bedrockIngot);
        OreDictionary.registerOre("blockBedrock", Blocks.BEDROCK);

		OreDictionary.registerOre("ingotTingot", new ItemStack(EkulItems.item_tingot));
		OreDictionary.registerOre("dustTingot", new ItemStack(EkulItems.item_tingotDust));
		OreDictionary.registerOre("nuggetTingot", new ItemStack(EkulItems.item_tingotNugget));

		OreDictionary.registerOre("ingotTiot", new ItemStack(EkulItems.item_tiot));
		OreDictionary.registerOre("dustTiot", new ItemStack(EkulItems.item_tiotDust));
		OreDictionary.registerOre("nuggetTiot", new ItemStack(EkulItems.item_tiotNugget));

		OreDictionary.registerOre("gemCoal", new ItemStack(Items.COAL));
	}


	public static void Recipes() {

    	GameRegistry.addSmelting(EkulBlocks.block_ekuliumOre, new ItemStack(EkulItems.item_ekulium), 5.0F);
    	GameRegistry.addSmelting(EkulBlocks.block_netherEkuliumOre, new ItemStack(EkulItems.item_ekulium, 2), 15.0F);
    	GameRegistry.addSmelting(EkulItems.item_tingotDust, new ItemStack(EkulItems.item_tingot), 0.0F);
    	GameRegistry.addSmelting(EkulItems.item_tiotDust, new ItemStack(EkulItems.item_tiot), 0.0F);
		
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EkulItems.item_diamondStick,2), "D", "D", 'D', "gemDiamond"));
    	GameRegistry.addRecipe(new ItemStack(EkulItems.item_runicStick),"RDR","BDB","RDR",'D',EkulItems.item_diamondStick,'R',EkulItems.item_runicGem,'B',Blocks.BEDROCK);
    	if(OreDictionary.doesOreNameExist("dustTin")){
        	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EkulItems.item_tingotDust,1),"TTT","TQT","TTT",'T',"dustTin",'Q',Items.SUGAR));
    	}
    	else{
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EkulItems.item_tingotDust,4),"TTT","TQT","TTT",'T',"ingotIron",'Q',Items.SUGAR));
    	}
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EkulItems.tool_delStaffCore),"PSA","DRD","ASP",'P',EkulItems.tool_ekuliumPick,'A',EkulItems.tool_ekuliumAxe,'S',EkulItems.tool_ekuliumShovel,'R',EkulBlocks.block_runicBlock,'D',"blockDiamond"));
    	GameRegistry.addRecipe(new ItemStack(EkulItems.tool_delStaff),"PCP","SRS","ARA",'R',EkulItems.item_runicStick,'C',EkulItems.tool_delStaffCore,'P',EkulItems.tool_runicPick,'S',EkulItems.tool_runicShovel,'A',EkulItems.tool_runicAxe);

    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EkulItems.item_WhixQuartz)," Q ","BMB"," Q ",'Q',"gemQuartz",'M',EkulItems.item_MonoWhix,'B',Blocks.BEDROCK));
    	GameRegistry.addRecipe(new ItemStack(EkulItems.item_tiot),"RQR","QTQ","RQR",'Q',EkulItems.item_WhixQuartz,'T',EkulItems.item_tingot,'R',EkulItems.item_runicGem);
    	
    	addCompressRecipe(new ItemStack(EkulBlocks.block_ekuliumBlock), new ItemStack(EkulItems.item_ekulium), 9);
    	addCompressRecipe(new ItemStack(EkulBlocks.block_runicBlock), new ItemStack(EkulItems.item_runicGem), 4);
    	addCompressRecipe(new ItemStack(EkulItems.item_tingot), new ItemStack(EkulItems.item_tingotNugget), 9);
    	addCompressRecipe(new ItemStack(EkulItems.item_tiot), new ItemStack(EkulItems.item_tiotNugget), 9);
    	addCompressRecipe(new ItemStack(Blocks.BEDROCK), new ItemStack(EkulItems.item_bedrockIngot), 9);

    	if(!Loader.isModLoaded("tconstruct")) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EkulBlocks.block_mix), "RER", "PBP", "RER", 'R', EkulBlocks.block_runicBlock, 'E', EkulBlocks.block_ekuliumBlock, 'B', Blocks.BEDROCK, 'P', EkulItems.item_WhixQuartz));
		}
    	
    	addToolSetRecipes(new ItemStack(EkulItems.item_ekulium),new ItemStack(EkulItems.item_diamondStick),new ItemStack(EkulItems.tool_ekuliumPick),
				    																				   new ItemStack(EkulItems.tool_ekuliumSword),
				    																				   new ItemStack(EkulItems.tool_ekuliumAxe),
				    																				   new ItemStack(EkulItems.tool_ekuliumShovel),
				    																				   new ItemStack(EkulItems.tool_ekuliumHoe));
    	addToolSetRecipes(new ItemStack(EkulItems.item_runicGem),new ItemStack(EkulItems.item_diamondStick),new ItemStack(EkulItems.tool_runicPick),
																									   	new ItemStack(EkulItems.tool_runicSword),
																									   	new ItemStack(EkulItems.tool_runicAxe),
																									   	new ItemStack(EkulItems.tool_runicShovel),
																									   	new ItemStack(EkulItems.tool_runicHoe));

        GameRegistry.addRecipe(new ItemStack(EkulItems.armor_runicHelm),"WEW","R R",'R',EkulBlocks.block_mix,'W',EkulItems.item_WhixQuartz,'E',Items.END_CRYSTAL);
        GameRegistry.addRecipe(new ItemStack(EkulItems.armor_runicChest),"R R","WEW","RRR",'R',EkulBlocks.block_mix,'W',EkulItems.item_WhixQuartz,'E',Items.END_CRYSTAL);
        GameRegistry.addRecipe(new ItemStack(EkulItems.armor_runicLegs),"RER","W W","R R",'R',EkulBlocks.block_mix,'W',EkulItems.item_WhixQuartz,'E',Items.END_CRYSTAL);
        GameRegistry.addRecipe(new ItemStack(EkulItems.armor_runicBoots),"W W","RER",'R',EkulBlocks.block_mix,'W',EkulItems.item_WhixQuartz,'E',Items.END_CRYSTAL);
	}
	public static void addCompressRecipe(ItemStack compressedStack, ItemStack downStack, int sqrSize){
		if(sqrSize == 4){
			GameRegistry.addRecipe(new ItemStack(compressedStack.getItem()), "AA","AA",'A',downStack);
			GameRegistry.addShapelessRecipe(new ItemStack(downStack.getItem(),4),compressedStack);
		}else if(sqrSize == 9){
			GameRegistry.addRecipe(new ItemStack(compressedStack.getItem()), "AAA","AAA","AAA",'A',downStack);
			GameRegistry.addShapelessRecipe(new ItemStack(downStack.getItem(),9),compressedStack);
		}
	}
	public static void addToolSetRecipes(ItemStack mat, ItemStack stick, ItemStack pick, ItemStack sword, ItemStack axe, ItemStack shovel, ItemStack hoe){
		GameRegistry.addRecipe(pick,"MMM"," S "," S ",'M',mat,'S',stick);
		GameRegistry.addRecipe(sword,"M","M","S",'M',mat,'S',stick);
		GameRegistry.addRecipe(axe,"MM","MS"," S",'M',mat,'S',stick);
		GameRegistry.addRecipe(shovel,"M","S","S",'M',mat,'S',stick);
		GameRegistry.addRecipe(hoe,"MM"," S"," S",'M',mat,'S',stick);
	}

}
