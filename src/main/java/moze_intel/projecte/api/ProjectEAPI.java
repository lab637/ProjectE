package moze_intel.projecte.api;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

/**
 * Class for basic mod interactions with ProjectE.<br>
 * For now, it's very simplistic, will be expanded in the future.<br>
 */
public final class ProjectEAPI
{
	public interface IRegisterCustomEMC {
		void registerCustomEMC(ItemStack stack, int emcValue);
	}
	/**
	 * Register an EMC value for the specified itemstack.<br>
	 * If the emcValue is <= 0, then the ItemStack will be blacklisted from any EMC mapping.<br>
	 * The ItemStack's NBT data is completely ignored in registration.<br>
	 * Users can still modify inter-mod EMC registration via command/configuration file.<br>
	 * Can be called during pre-init, init or post-init.
	 */
	public static void registerCustomEMC(ItemStack stack, int emcValue)
	{
		try {
			Class<?> clazz = Class.forName("moze_intel.projecte.emc.mappers.APICustomEMCMapper");
			IRegisterCustomEMC instance = (IRegisterCustomEMC)clazz.getField("instance").get(null);
			instance.registerCustomEMC(stack, emcValue);
		} catch (Throwable t) {

		}
	}

	/**
	 * Blacklist an entity for the interdiction torches.<br> 
	 * Can be called during pre-init, init or post-init.
	 */
	public static void registerInterdictionBlacklist(Class entityClass)
	{
		FMLInterModComms.sendMessage("ProjectE", "interdictionblacklist", entityClass.getCanonicalName());
	}

	/**
	 * Make an ItemStack keep it's NBT data when condensed.<br>
	 * Can be called during pre-init, init or post-init.
	 */
	public static void registerCondenserNBTException(ItemStack stack)
	{
		FMLInterModComms.sendMessage("ProjectE", "condensernbtcopy", stack);
	}
}
