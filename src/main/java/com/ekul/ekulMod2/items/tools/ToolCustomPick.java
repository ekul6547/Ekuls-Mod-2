package com.ekul.ekulMod2.items.tools;

import com.ekul.ekulMod2.main.EkulMod;
import com.ekul.ekulMod2.main.ItemModelProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class ToolCustomPick extends ItemPickaxe implements ItemModelProvider {
	protected String name;

	public ToolCustomPick(ToolMaterial mat, String name) {
		super(mat);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.name = name;
	}
	@Override
	public void registerItemModel(Item item) {
		EkulMod.proxy.registerItemRenderer(this, 0, name);
	}

}
