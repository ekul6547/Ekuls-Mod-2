package com.ekul.ekulMod2.main;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends ServerProxy{
	
	public void registerRenderThings(){

		//RenderingRegistry.registerEntityRenderingHandler(EntityWhixler.class, new RenderEntityW(new ModelWhixler(),0, "ekul:textures/entity/Whixler.png"));
		//RenderingRegistry.registerEntityRenderingHandler(EntityUsefulZombie.class, new RenderEntityUZ(new ModelUsefulZombie(),0,"ekul:textures/entity/UsefulZombie.png"));
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation("ekul:" + id, "inventory"));
	}

}
