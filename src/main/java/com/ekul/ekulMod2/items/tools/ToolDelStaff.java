package com.ekul.ekulMod2.items.tools;

import com.ekul.ekulMod2.items.ItemBlank;
import com.ekul.ekulMod2.main.CustomDamageSource;
import com.ekul.ekulMod2.main.EkulItems;
import com.modHelpers.HelperTESLA;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Loader;

import java.util.List;

public class ToolDelStaff extends ItemBlank {
    boolean powered = Loader.isModLoaded("tesla");

	public ToolDelStaff(String name){
		super(name);
		this.setMaxStackSize(1);
		if(powered){
		    this.setMaxDamage(27);
        }else {
            this.setMaxDamage(2048);
        }
		this.isDamageable();
	}

	public void damage(ItemStack stack, int amount, EntityPlayer player){
        if(powered){
            HelperTESLA.takePower(stack,amount*10);
        }else {
            stack.damageItem(amount, player);
        }
    }

    public boolean canDamage(ItemStack stack, int amount){
	    if(powered){
	        return HelperTESLA.canTake(stack, amount*10);
        }else{
	        return stack.getItemDamage() > 0;
        }
    }

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand)
	{
	    if(canDamage(stack, 1) && target.isNonBoss()) {
            float damage = target.getHealth();
            target.attackEntityFrom(new CustomDamageSource("deletion").setDamageBypassesArmor().setDamageIsAbsolute().setDamageAllowedInCreativeMode(), damage);
            damage(stack, 2, player);
        }
        return true;
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
        if(!powered){
			int runicDamage = 8;
			int lifeDamage = 4;
			int lifeTake = 1;
			if(player.isSneaking()){
			if(player.inventory.hasItemStack(new ItemStack(EkulItems.item_runicGem)) && stack.getItemDamage()-runicDamage > 0){
				player.inventory.decrStackSize(player.inventory.getSlotFor(new ItemStack(EkulItems.item_runicGem)),1);
				stack.damageItem(-runicDamage, player);
			}
			else
				if(stack.getItemDamage() > 0){
			float health = 0;
			if(player.getHealth() <= lifeTake){
				health = 0;
			}
			else{
				health = lifeTake;
			}
				player.setHealth(player.getHealth()-health);
				stack.damageItem(-lifeDamage, player);
			}}
        }
        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if(powered) {
            new HelperTESLA.displayInfo(stack, player, tooltip, advanced);
        }
    }

	@Override
	public ICapabilityProvider initCapabilities(final ItemStack stack, NBTTagCompound nbt) {
        if(powered) {
            return new HelperTESLA.powerProvider(stack, 20000, 200, 200);
        }else{
            return null;
        }
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){

		if (player.canPlayerEdit(pos, facing, stack)) {
			if(player.isSneaking()){
				int tx = pos.getX();
				int ty = pos.getY();
				int tz = pos.getZ();
				int limit = 49;
				int[][] BlocksList = new int[limit+1][3];
				int dataCount = 0;
	            BlocksList[dataCount][0] = tx;
	            BlocksList[dataCount][1] = ty;
	            BlocksList[dataCount][2] = tz;
				dataCount ++;
				int posCount = 0;
				Block tarBlock = world.getBlockState(pos).getBlock();
				boolean breakBlock = true;
				int BlockCount = 125;
				while(breakBlock == true){
					for(int yb=-1;yb<=1;yb++){
						for(int xb=-1;xb<=1;xb++){
							for(int zb=-1;zb<=1;zb++){
							    BlockPos breakPos = new BlockPos(tx+xb,ty+yb,tz+zb);
								if(world.getBlockState(breakPos).getBlock() == tarBlock && dataCount < limit && canDamage(stack, 1)){
									boolean canBreak = true;
					            	damage(stack,1, player);
					            	Block blockIs = world.getBlockState(new BlockPos(tx+xb,ty+yb,tz+zb)).getBlock();
					            	if(breakPos.getY() < 1 || (breakPos.getY() <= 2 && blockIs == Blocks.BEDROCK)){
					            		canBreak = false;
					            	}
					            	if(canBreak == true){
                                        List<ItemStack> drops = world.getBlockState(breakPos).getBlock().getDrops(world,breakPos,world.getBlockState(breakPos),1);
                                        for(ItemStack item : drops) {
                                            player.inventory.addItemStackToInventory(item);
                                        }
					            	     //drops.dropBlockAsItem(world, new BlockPos((int) player.posX,(int) player.posY,(int) player.posZ), world.getBlockState(new BlockPos(tx+xb,ty+yb,tz+zb)), 0);
                                        world.destroyBlock(breakPos, false);
                                        BlocksList[dataCount][0] = tx+xb;
                                        BlocksList[dataCount][1] = ty+yb;
                                        BlocksList[dataCount][2] = tz+zb;
                                        dataCount ++;
					            	}
								}
					}}}
					if(dataCount>0){
					posCount ++;
					tx = BlocksList[posCount][0];
					ty = BlocksList[posCount][1];
					tz = BlocksList[posCount][2];
					}
					if(BlockCount <= 0 || posCount >= dataCount || !canDamage(stack, 1)){
						breakBlock = false;
					}
					BlockCount --;
				}
			}else{
				if(pos.getY() >= 1 && canDamage(stack, 1)){
					List<ItemStack> drops = world.getBlockState(pos).getBlock().getDrops(world,pos,world.getBlockState(pos),1);
					for(ItemStack item : drops) {
                        player.inventory.addItemStackToInventory(item);
                    }
					//drops.dropBlockAsItem(world, new BlockPos(player.posX, player.posY,player.posZ), world.getBlockState(pos), 1);
					world.destroyBlock(pos, false);
					damage(stack,1, player);
				}
            }
	}
		
		return EnumActionResult.SUCCESS;
		
	}
}