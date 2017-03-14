package com.ekul.ekulMod2.main;

import com.modHelpers.HelperLoot;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EkulEventHandler {

    @SubscribeEvent
    public void lootTableLoad(LootTableLoadEvent event){
        if(event.getName().getResourcePath().substring(0,5).equals("chests".substring(0,5))) {
            LootTable loot = event.getTable();
            HelperLoot.addItemToTable(loot,EkulItems.item_WhixSeed,1,1F,0.1F,1,5,0F,1F,"ekul:itemWhixSeed");
        }
        if(event.getName().getResourcePath().equals("entities/sheep/light_blue")){
            LootTable loot = event.getTable();
            HelperLoot.addItemToTable(loot,EkulItems.item_WhixSeed,1,1F,0.1F,0,1,0F,1F,"ekul:itemWhixSeed");
        }
    }
}
