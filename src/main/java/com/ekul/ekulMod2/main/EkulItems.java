package com.ekul.ekulMod2.main;

import com.ekul.ekulMod2.items.ItemBlank;
import com.ekul.ekulMod2.items.ItemBlankSeed;
import com.ekul.ekulMod2.items.armor.ArmorRunicRunicBoots;
import com.ekul.ekulMod2.items.armor.ArmorRunicRunicChest;
import com.ekul.ekulMod2.items.armor.ArmorRunicRunicHelm;
import com.ekul.ekulMod2.items.armor.ArmorRunicRunicLegs;
import com.ekul.ekulMod2.items.tools.*;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.ekul.ekulMod2.main.EkulMod.armor_runicArmor;

public class EkulItems {

    //Items
    public static Item item_ekulium;
    public static Item item_runicGem;
    public static Item item_diamondStick;
    public static Item item_runicStick;
    public static Item item_tingot;
    public static Item item_tingotNugget;
    public static Item item_tingotDust;
    public static Item item_tiot;
    public static Item item_tiotNugget;
    public static Item item_tiotDust;
    public static Item item_MonoWhix;
    public static ItemBlankSeed item_WhixSeed;
    public static Item item_WhixQuartz;
    public static Item item_bedrockIngot;

    //Tools
    public static Item tool_ekuliumPick;
    public static Item tool_ekuliumSword;
    public static Item tool_ekuliumAxe;
    public static Item tool_ekuliumShovel;
    public static Item tool_ekuliumHoe;

    public static Item tool_runicPick;
    public static Item tool_runicSword;
    public static Item tool_runicAxe;
    public static Item tool_runicShovel;
    public static Item tool_runicHoe;

    public static Item tool_delStaff;
    public static Item tool_delStaffCore;
    
    public static Item armor_runicHelm;
    public static Item armor_runicChest;
    public static Item armor_runicLegs;
    public static Item armor_runicBoots;

    public static void initItems(){

        item_ekulium = 		    registerItem(new ItemBlank("itemEkulium").setCreativeTab(EkulMod.tabEkulMod));
        item_runicGem = 		registerItem(new ItemBlank("itemRunicGem").setCreativeTab(EkulMod.tabEkulMod));
        item_diamondStick =	    registerItem(new ItemBlank("itemDiamondStick").setCreativeTab(EkulMod.tabEkulMod));
        item_runicStick = 		registerItem(new ItemBlank("itemRunicStick").setCreativeTab(EkulMod.tabEkulMod));
        item_bedrockIngot =     registerItem(new ItemBlank("itemBedrockIngot").setCreativeTab(EkulMod.tabEkulMod));

        item_tingot = 			registerItem(new ItemBlank("itemTingot").setCreativeTab(EkulMod.tabEkulMod));
        item_tingotDust = 		registerItem(new ItemBlank("itemTingotDust").setCreativeTab(EkulMod.tabEkulMod));
        item_tingotNugget = 	registerItem(new ItemBlank("itemTingotNugget").setCreativeTab(EkulMod.tabEkulMod));

        item_tiot = 			registerItem(new ItemBlank("itemTiot").setCreativeTab(EkulMod.tabEkulMod));
        item_tiotDust = 		registerItem(new ItemBlank("itemTiotDust").setCreativeTab(EkulMod.tabEkulMod));
        item_tiotNugget = 		registerItem(new ItemBlank("itemTiotNugget").setCreativeTab(EkulMod.tabEkulMod));

        item_MonoWhix = 		registerItem(new ItemBlank("itemMonoWhix","Temporary Recipe").setCreativeTab(EkulMod.tabEkulMod));
        item_WhixQuartz = 		registerItem(new ItemBlank("itemWhixQuartz").setCreativeTab(EkulMod.tabEkulMod));
        item_WhixSeed =         registerItem(new ItemBlankSeed("itemWhixSeed", EkulBlocks.block_whixCrop, Blocks.FARMLAND));
        item_WhixSeed.setCreativeTab(EkulMod.tabEkulMod);

        tool_ekuliumPick = 	    registerItem(new ToolCustomPick(EkulMod.Mat_Ekulium,"toolEkuliumPick").setCreativeTab(EkulMod.tabEkulMod));
        tool_ekuliumSword = 	registerItem(new ToolCustomSword(EkulMod.Mat_Ekulium,"toolEkuliumSword").setCreativeTab(EkulMod.tabEkulMod));
        tool_ekuliumAxe = 		registerItem(new ToolCustomAxe(EkulMod.Mat_Ekulium,"toolEkuliumAxe").setCreativeTab(EkulMod.tabEkulMod));
        tool_ekuliumShovel = 	registerItem(new ToolCustomShovel(EkulMod.Mat_Ekulium,"toolEkuliumShovel").setCreativeTab(EkulMod.tabEkulMod));
        tool_ekuliumHoe =		registerItem(new ToolCustomHoe(EkulMod.Mat_Ekulium,"toolEkuliumHoe").setCreativeTab(EkulMod.tabEkulMod));

        tool_runicPick = 		registerItem(new ToolCustomPick(EkulMod.Mat_Runic,"toolRunicPick").setCreativeTab(EkulMod.tabEkulMod));
        tool_runicSword = 		registerItem(new ToolCustomSword(EkulMod.Mat_Runic,"toolRunicSword").setCreativeTab(EkulMod.tabEkulMod));
        tool_runicAxe =		    registerItem(new ToolCustomAxe(EkulMod.Mat_Runic,"toolRunicAxe").setCreativeTab(EkulMod.tabEkulMod));
        tool_runicShovel = 	    registerItem(new ToolCustomShovel(EkulMod.Mat_Runic,"toolRunicShovel").setCreativeTab(EkulMod.tabEkulMod));
        tool_runicHoe =		    registerItem(new ToolCustomHoe(EkulMod.Mat_Runic,"toolRunicHoe").setCreativeTab(EkulMod.tabEkulMod));

        tool_delStaff = 		registerItem(new ToolDelStaff("toolDelStaff").setCreativeTab(EkulMod.tabEkulMod));
        tool_delStaffCore = 	registerItem(new ToolDelStaffCore("toolDelStaffCore").setCreativeTab(EkulMod.tabEkulMod));

        armor_runicHelm = 		registerItem(new ArmorRunicRunicHelm(armor_runicArmor, EntityEquipmentSlot.HEAD,"armorRunicHelm").setCreativeTab(EkulMod.tabEkulMod));
        armor_runicChest = 	    registerItem(new ArmorRunicRunicChest(armor_runicArmor, EntityEquipmentSlot.CHEST,"armorRunicChest").setCreativeTab(EkulMod.tabEkulMod));
        armor_runicLegs = 		registerItem(new ArmorRunicRunicLegs(armor_runicArmor, EntityEquipmentSlot.LEGS,"armorRunicLegs").setCreativeTab(EkulMod.tabEkulMod));
        armor_runicBoots = 	    registerItem(new ArmorRunicRunicBoots(armor_runicArmor, EntityEquipmentSlot.FEET,"armorRunicBoots").setCreativeTab(EkulMod.tabEkulMod));
    }

    private static <T extends Item> T registerItem(T item) {
        GameRegistry.register(item);
        ((ItemModelProvider)item).registerItemModel(item);
        return item;
    }
}
