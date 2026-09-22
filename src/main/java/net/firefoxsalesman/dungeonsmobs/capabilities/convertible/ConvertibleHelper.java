package net.firefoxsalesman.dungeonsmobs.capabilities.convertible;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.firefoxsalesman.dungeonsmobs.capabilities.ModCapabilities;
import net.firefoxsalesman.dungeonsmobs.entity.ModEntities;
import net.firefoxsalesman.dungeonsmobs.entity.water.SunkenSkeletonEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.neoforged.neoforge.event.EventHooks;

public class ConvertibleHelper {
	private static Method handleAttributesMethod;
	private static Method supportsBreakDoorGoalMethod;

	static {
		try {
			handleAttributesMethod = Zombie.class.getDeclaredMethod("handleAttributes", float.class);
			handleAttributesMethod.setAccessible(true);
		} catch (NoSuchMethodException e) {
			handleAttributesMethod = null;
		}

		try {
			supportsBreakDoorGoalMethod = Zombie.class.getDeclaredMethod("supportsBreakDoorGoal");
			supportsBreakDoorGoalMethod.setAccessible(true);
		} catch (NoSuchMethodException e) {
			supportsBreakDoorGoalMethod = null;
		}
	}

	public static Convertible getConvertibleCapability(Entity entity) {
		return entity.getData(ModCapabilities.CONVERTIBLE);
	}

	public static void onDrownedAndConvertedTo(Mob original, Mob convertedTo) {
		if (original instanceof AbstractSkeleton && convertedTo instanceof SunkenSkeletonEntity) {
			if (!original.isSilent()) {
				original.level().levelEvent(null, 1040, original.blockPosition(), 0);
			}
			DungeonsMobs.LOGGER.info("Converted {} to {}", original, convertedTo);
		}

		if (original instanceof Zombie && convertedTo instanceof Zombie) {
			Zombie originalZombie = (Zombie) original;
			Zombie convertedToZombie = (Zombie) convertedTo;
			handleZombieAttributes(convertedToZombie);
			setZombieCanBreakDoors(originalZombie, convertedToZombie);
		}
		EventHooks.onLivingConvert(original, convertedTo);
	}

	private static void handleZombieAttributes(Zombie convertedToZombie) {
		if (handleAttributesMethod != null) {
			try {
				handleAttributesMethod.invoke(convertedToZombie,
						convertedToZombie.level()
								.getCurrentDifficultyAt(convertedToZombie.blockPosition())
								.getSpecialMultiplier());
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	private static void setZombieCanBreakDoors(Zombie originalZombie, Zombie convertedToZombie) {
		if (supportsBreakDoorGoalMethod != null) {
			try {
				convertedToZombie.setCanBreakDoors(
						(Boolean) supportsBreakDoorGoalMethod.invoke(convertedToZombie)
								&& originalZombie.canBreakDoors());
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	public static EntityType<? extends Mob> getDrowningConvertTo(Mob mob) {
		EntityType<? extends Mob> convertToType = (EntityType<? extends Mob>) mob.getType();
		if (mob instanceof AbstractSkeleton) {
			convertToType = ModEntities.SUNKEN_SKELETON.get();
		}
		return convertToType;
	}

	public static boolean convertsInWater(Mob mobEntity) {
		return mobEntity.getTags().contains("converts_in_water");
	}
}
