package com.ekul.ekulMod2.items;

import com.ekul.ekulMod2.main.EkulMod;
import com.ekul.ekulMod2.main.ItemModelProvider;
import com.google.common.collect.Multimap;
import com.modHelpers.HelperTESLA;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class ItemArmorRunic extends net.minecraft.item.ItemArmor implements ItemModelProvider, ISpecialArmor {

    boolean powered = Loader.isModLoaded("tesla");
    private String name;
    protected int repairCount = 0;
    protected ArmorMaterial mat;
    int deletionProtectionCost = 200;
    int powerPerDamage = 5;
    int maxCapacity;
    int powerCount = 0;

    public ItemArmorRunic(ArmorMaterial material, EntityEquipmentSlot slot, String name) {
        super(material, 0, slot);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.name = name;
        this.mat = material;
        if(powered){
            this.setMaxDamage(27);
        }
        maxCapacity = powerPerDamage*40000;
    }

    @Override
    public ICapabilityProvider initCapabilities(final ItemStack stack, NBTTagCompound nbt) {
        if(powered) {
            return new HelperTESLA.powerProvider(stack, maxCapacity, 200, 200);
        }else{
            return null;
        }
    }
    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if(powered) {
            new HelperTESLA.displayInfo(stack, player, tooltip, advanced);
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        if(entity.isInvisible()){
            if(powered){
                HelperTESLA.takePower(stack,1);
            }
            return "ekul:textures/models/armor/armor_invis_layer_" + (slot == EntityEquipmentSlot.LEGS ? "2" : "1") + ".png";
        }
        else{
            return null;
        }
    }
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
        if(itemStack.isItemDamaged() && !powered){
            if(repairCount >= 10){
                itemStack.setItemDamage(itemStack.getItemDamage()-1);
                repairCount = 0;
            }else{
                repairCount ++;
            }
        }else{
            repairCount = 0;
        }
        if(powered){
            if(powerCount >= 300){
                HelperTESLA.takePower(itemStack,1);
                powerCount = 0;
            }else{
                powerCount ++;
            }
        }else{
            powerCount = 0;
        }

        if(this.armorType == EntityEquipmentSlot.HEAD){//Helmet
            player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 100, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 100 , 2));
            player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 205 , 2));
            boolean canViewMine = false;
            if(powered){
                if(HelperTESLA.canTake(itemStack, 5)){
                    canViewMine = true;
                }
            }else{
                canViewMine = true;
            }

		    RayTraceResult ray = this.rayTrace(world, player, true);
            try {
                if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK && canViewMine) {
                    boolean mined = false;
                    BlockPos pos = ray.getBlockPos();
                    IBlockState block = world.getBlockState(pos);
                    int metadata = block.getBlock().getMetaFromState(block);
                    if (!world.isAirBlock(pos) && block.getBlock() != null && block.isFullBlock()) {
                        ItemStack blockStack = new ItemStack(block.getBlock());
                        /*System.out.println(block);
                        System.out.println(block.getBlock());
                        System.out.println(blockStack);
                        System.out.println(blockStack.getItem());
                        System.out.println();*/
                        if (blockStack != null && blockStack.getItem() != null) {
                            int[] OrdId = OreDictionary.getOreIDs(blockStack);
                            if (OrdId.length > 0) {
                                String Ord = (OreDictionary.getOreName(OrdId[0]));
                                if (OreDictionary.doesOreNameExist(Ord)) {
                                    Block blockTo = Blocks.STONE;
                                    int multi = 1;
                                    if (world.provider.getDimension() == -1) {
                                        blockTo = Blocks.NETHERRACK;
                                        multi = 2;
                                    }
                                    if(Ord.toLowerCase().contains("lapis")){
                                        metadata = 4;
                                    }
                                    String OrdTo = "gem" + Ord.substring(3);
                                    if (OreDictionary.doesOreNameExist(OrdTo)) {
                                        List<ItemStack> ingotItem = (OreDictionary.getOres(OrdTo));
                                        if (ingotItem.size() > 0) {
                                            world.setBlockState(pos, blockTo.getDefaultState());
                                            if(metadata > 0) {
                                                player.inventory.addItemStackToInventory(new ItemStack(ingotItem.get(0).getItem(), 3*multi, metadata));
                                            }else{
                                                player.inventory.addItemStackToInventory(new ItemStack(ingotItem.get(0).getItem(), 3*multi));
                                            }
                                            mined = true;
                                        }
                                    } else {
                                        OrdTo = "ingot" + Ord.substring(3);
                                        if (OreDictionary.doesOreNameExist(OrdTo)) {
                                            List<ItemStack> ingotItem = (OreDictionary.getOres(OrdTo));
                                            if (ingotItem.size() > 0) {
                                                world.setBlockState(pos, blockTo.getDefaultState());
                                                if(metadata > 0) {
                                                    player.inventory.addItemStackToInventory(new ItemStack(ingotItem.get(0).getItem(), 2*multi, metadata));
                                                }else{
                                                    player.inventory.addItemStackToInventory(new ItemStack(ingotItem.get(0).getItem(), 2*multi));
                                                }
                                                mined = true;
                                            }
                                        } else {
                                            OrdTo = "dust" + Ord.substring(3);
                                            if (OreDictionary.doesOreNameExist(OrdTo)) {
                                                List<ItemStack> ingotItem = (OreDictionary.getOres(OrdTo));
                                                if (ingotItem.size() > 0) {
                                                    world.setBlockState(pos, blockTo.getDefaultState());
                                                    if(metadata > 0) {
                                                        player.inventory.addItemStackToInventory(new ItemStack(ingotItem.get(0).getItem(), 5*multi, metadata));
                                                    }else{
                                                        player.inventory.addItemStackToInventory(new ItemStack(ingotItem.get(0).getItem(), 5*multi));
                                                    }
                                                    mined = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(powered && mined){
                        HelperTESLA.takePower(itemStack,5);
                    }
                }
            }catch(java.lang.IndexOutOfBoundsException out){
                System.out.println("EKUL:RUNICHELM:"+out.getCause());
            }
        }
        if(this.armorType == EntityEquipmentSlot.CHEST){//Chetsplate
            player.addPotionEffect((new PotionEffect(MobEffects.STRENGTH, 100, 4)));
            player.addPotionEffect((new PotionEffect(MobEffects.HASTE, 100, 8)));
            /*if(!powered) {
                player.setHealth(player.getHealth() + 1);
            }*/
            player.extinguish();
        }
        if(this.armorType == EntityEquipmentSlot.LEGS){//Leggings
            player.addPotionEffect((new PotionEffect(MobEffects.SPEED, 100, 8)));
            player.addPotionEffect((new PotionEffect(MobEffects.JUMP_BOOST, 100, 4)));
        }
        if(this.armorType == EntityEquipmentSlot.FEET){//Boots
            player.fallDistance = 0.0f;
        }

    }
    @Override
    public void registerItemModel(Item item) {
        EkulMod.proxy.registerItemRenderer(this, 0, name);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        int damageRatio = 0;
        int maxDamage = 0;
        if(powered) {
            damageRatio = 1;
            maxDamage = (int) (HelperTESLA.getPower(armor) / powerPerDamage);
        }else{
            damageRatio = mat.getDamageReductionAmount(intToEquipmentSlot(slot));
            maxDamage = mat.getDurability(intToEquipmentSlot(slot));
        }
        return new ArmorProperties(0,damageRatio,maxDamage);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        double toRet = (mat.getDamageReductionAmount(intToEquipmentSlot(slot)));
        if(powered){
            toRet = (mat.getDurability(intToEquipmentSlot(slot)));
            toRet = toRet * ((float) HelperTESLA.getPower(armor) / (float) HelperTESLA.getMaxPower(armor));
        }
        return (int) toRet;
    }

    public EntityEquipmentSlot intToEquipmentSlot(int slot){
        if(slot == 0) return EntityEquipmentSlot.FEET;
        if(slot == 1) return EntityEquipmentSlot.LEGS;
        if(slot == 2) return EntityEquipmentSlot.CHEST;
        if(slot == 3) return EntityEquipmentSlot.HEAD;
        else{
            return null;
        }
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        int newDamage = damage;
        if(powered) {
            if (source.getDamageType() == "deletion") {
                if (HelperTESLA.canTake(stack, deletionProtectionCost)) {
                    HelperTESLA.takePower(stack, deletionProtectionCost);
                    newDamage = 0;
                }
            } else {
                for (int i = 0; i < damage; i++) {
                    if (HelperTESLA.canTake(stack, powerPerDamage)) {
                        HelperTESLA.takePower(stack, powerPerDamage);
                        newDamage -= 1;
                    }
                }
            }
            stack.setItemDamage(0);
        }else{
            newDamage = damage-mat.getDamageReductionAmount(intToEquipmentSlot(slot));
        }
        if (newDamage > 0) {
            entity.attackEntityFrom(source.setDamageBypassesArmor(), newDamage);
        }
    }
}