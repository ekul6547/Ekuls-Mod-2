package com.modHelpers;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainerProvider;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import java.util.List;

/**
 * Called from within the item code
 * @author ekul6547
 *
 * put in Item code:

   boolean powered = Loader.isModLoaded("tesla"); //true if mod loaded(obviously). Put all HelperTESLA in an if statement of this to allow items to exist without power

   @Override
   public ICapabilityProvider initCapabilities(final ItemStack stack, NBTTagCompound nbt) {
       if(powered) {
           return new HelperTESLA.powerProvider(stack, maxCapacity, inputRate, outputRate);
       }else{
           return null;
       }
   }
 */
public class HelperTESLA {

    public static class powerProvider extends BaseTeslaContainerProvider{
        public powerProvider(final ItemStack stack, int capacity, int input, int output){
            super(new BaseTeslaContainer(20000, 20, 20){
                @Override
                public long givePower(long Tesla, boolean simulated) {
                    //setDamage(stack, 0);
                    return super.givePower(Tesla, simulated);
                }

                @Override
                public long takePower(long Tesla, boolean simulated) {
                    //setDamage(stack, 0);
                    return super.takePower(Tesla, simulated);
                }
            });
        }
    }

    public static class displayInfo {
        public displayInfo(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
            final BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
            stack.setItemDamage(((int) (container.getStoredPower()/container.getCapacity())*stack.getMaxDamage()));
            tooltip.add("Power: " + container.getStoredPower() + "/" + container.getCapacity());
        }
    }

    /**
     * Take power from stack
     * @param stack
     * @param amount
     * @return returns the amount taken (so if only 8 is left, and you asked for 20, it will return 8)
     * Also wont return above the output rate
     */
    public static long takePower(ItemStack stack, int amount) {
        BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
        return container.takePower(amount, false);
    }

    /**
     * Check if you can take from stack
     * @param stack
     * @param amount
     * @return returns true if you can take a certain amount of power from a container
     */
    public static boolean canTake(ItemStack stack, int amount){
        BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
        return (getPower(stack) - amount >= 0);
    }

    /**
     * Give power to stack
     * @param stack
     * @param amount
     * @return returns the amount accepted (so if you gave it 20, and it could only support 8 more, it will return 8)
     * (so do amount - returned amount to see non-accepted energy)
     * Also wont accept over input rate
     */
    public static long givePower(ItemStack stack, int amount) {
        BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
        return container.givePower(amount, false);
    }

    /**
     * Check if you can give to stack
     * @param stack
     * @param amount
     * @return returns true if you can give a certain amount of power to a container
     */
    public static boolean canGive(ItemStack stack, int amount){
        BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
        return (getPower(stack) + amount <= getMaxPower(stack));
    }

    public static long getPower(ItemStack stack) {
        BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
        return container.getStoredPower();
    }
    public static long getMaxPower(ItemStack stack){
        BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
        return container.getCapacity();
    }
    public static long getInputRate(ItemStack stack){
        BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
        return container.getInputRate();
    }
    public static long getOutputRate(ItemStack stack){
        BaseTeslaContainer container = (BaseTeslaContainer) stack.getCapability(TeslaCapabilities.CAPABILITY_HOLDER, EnumFacing.DOWN);
        return container.getOutputRate();
    }
}
