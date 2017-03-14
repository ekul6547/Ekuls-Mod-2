package com.ekul.ekulMod2.main;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class CustomEntityDamageSource extends EntityDamageSource{

	public CustomEntityDamageSource(String name, Entity entity) {
		super(name, entity);
	}

}
