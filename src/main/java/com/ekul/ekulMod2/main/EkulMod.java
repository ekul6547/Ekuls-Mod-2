package com.ekul.ekulMod2.main;

import com.ekul.ekulMod2.worldGen.EkulGenOreGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ModData.MODID,name = ModData.NAME, version = ModData.VERSION)
public class EkulMod
{
    
    @Mod.Instance(ModData.MODID)
    public static EkulMod mod_Instance;
    
    @SidedProxy(clientSide="com.ekul.ekulMod2.main.ClientProxy", serverSide="com.ekul.ekulMod2.main.ServerProxy")
    public static ServerProxy proxy;

    public static Item.ToolMaterial Mat_Ekulium;
    public static Item.ToolMaterial Mat_Runic;
    public static ItemArmor.ArmorMaterial armor_runicArmor;
    
    public static DamageSource damageSourceUseful;
    
    @Mod.Metadata

    public static ModMetadata meta;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.registerRenderThings();
    	//Item/Block and registering

    	//Config handling
    	Mat_Ekulium = EnumHelper.addToolMaterial("MatEkulium", 4, 2048, 12, 5, 22);
    	Mat_Runic = EnumHelper.addToolMaterial("MatRunic", 6, 6192, 16, 9, 28);
        armor_runicArmor = EnumHelper.addArmorMaterial("RUNIC","ekul:armor_Runic",64,new int[]{10,10,10,10},10, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,5f);

        EkulBlocks.initBlocks();
        EkulItems.initItems();

        GameRegistry.registerWorldGenerator(new EkulGenOreGen(), 0);

        //registerEntity(EntityWhixler.class,"Whixler",0xC7C7C7,0x679357,100,3,7,EnumCreatureType.monster);
        //registerEntity(EntityUsefulZombie.class,"UsefulZombie",0x009999,0xf80000,50,1,16,EnumCreatureType.monster);

    	//damageSourceUseful = new EntityDamageSource("damageMadeUseful", null);

        EkulModRecipes.OreDic();
        EkulModIntegration.LoadEMC();
        EkulModIntegration.LoadJEI();
        EkulModIntegration.LoadTinker();
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//Proxy, Crafting, TileEntity, entity, GUI and Packet Registering

        MinecraftForge.EVENT_BUS.register(new EkulEventHandler());

    	
    	EkulModRecipes.Recipes();

        EkulModIntegration.LoadIC2();


    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	//Other mod crossover
        EkulModIntegration.specialEMC();
    }

    
    public static CreativeTabs tabEkulMod = new CreativeTabs("tabEkulMod"){
    	@Override
    	public Item getTabIconItem(){
    		return new ItemStack(EkulItems.item_ekulium).getItem();
    	}
    };
}
