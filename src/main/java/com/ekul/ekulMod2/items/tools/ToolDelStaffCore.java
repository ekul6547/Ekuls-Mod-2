package com.ekul.ekulMod2.items.tools;

import com.ekul.ekulMod2.items.ItemBlank;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ToolDelStaffCore extends ItemBlank {

	public ToolDelStaffCore(String name){
		super(name);
		this.setMaxStackSize(1);
		this.setMaxDamage(1);
		this.isDamageable();
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z){
		
		if (player.canPlayerEdit(pos, facing, stack) && y >= 2) {
				Block drops = world.getBlockState(pos).getBlock();
				drops.dropBlockAsItem(world, new BlockPos((int) player.posX,(int) player.posY,(int) player.posZ), world.getBlockState(pos), 1);
				world.destroyBlock(pos, false);
				stack.damageItem(2, player);

	}
		return EnumActionResult.SUCCESS;
		
	}
}