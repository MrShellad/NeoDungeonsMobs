package net.firefoxsalesman.dungeonsmobs.capabilities.properties;

import net.firefoxsalesman.dungeonsmobs.capabilities.ModCapabilities;
import net.minecraft.world.entity.Entity;

public class MobPropsHelper {

	public static MobProps getMobPropsCapability(Entity entity) {
		return entity.getData(ModCapabilities.MOB_PROPS);
	}
}
