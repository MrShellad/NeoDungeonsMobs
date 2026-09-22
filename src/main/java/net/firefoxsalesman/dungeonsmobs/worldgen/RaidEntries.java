package net.firefoxsalesman.dungeonsmobs.worldgen;

import java.util.Arrays;
import java.util.function.Supplier;

import net.firefoxsalesman.dungeonsmobs.config.DungeonsMobsConfig;
import net.firefoxsalesman.dungeonsmobs.entity.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public class RaidEntries {

	private static final int[] ARMORED_PILLAGER_SPAWNS = new int[] { 0, 1, 2, 0, 3, 1, 0, 3 };
	public static final EnumProxy<Raid.RaiderType> ARMORED_PILLAGER = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) ModEntities.ARMORED_PILLAGER::get,
			ARMORED_PILLAGER_SPAWNS);

	private static final int[] ARMORED_VINDICATOR_SPAWNS = new int[] { 0, 0, 1, 2, 0, 1, 1, 2 };
	public static final EnumProxy<Raid.RaiderType> ARMORED_VINDICATOR = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) ModEntities.ARMORED_VINDICATOR::get,
			ARMORED_VINDICATOR_SPAWNS);

	private static final int[] MOUNTAINEER_SPAWNS = new int[] { 0, 0, 2, 0, 1, 4, 2, 5 };
	public static final EnumProxy<Raid.RaiderType> MOUNTAINEER = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) ModEntities.MOUNTAINEER::get,
			MOUNTAINEER_SPAWNS);

	private static final int[] ROYAL_GUARD_SPAWNS = new int[] { 0, 0, 1, 0, 0, 2, 1, 2 };
	public static final EnumProxy<Raid.RaiderType> ROYAL_GUARD = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) ModEntities.ROYAL_GUARD::get,
			ROYAL_GUARD_SPAWNS);

	private static final int[] GEOMANCER_SPAWNS = new int[] { 0, 0, 0, 0, 0, 1, 1, 2 };
	public static final EnumProxy<Raid.RaiderType> GEOMANCER = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) ModEntities.GEOMANCER::get,
			GEOMANCER_SPAWNS);

	private static final int[] MAGE_SPAWNS = new int[] { 0, 0, 1, 0, 0, 1, 0, 2 };
	public static final EnumProxy<Raid.RaiderType> MAGE = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) ModEntities.MAGE::get,
			MAGE_SPAWNS);

	private static final int[] ILLUSIONER_SPAWNS = new int[] { 0, 0, 0, 0, 0, 1, 1, 2 };
	public static final EnumProxy<Raid.RaiderType> ILLUSIONER = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) () -> EntityType.ILLUSIONER,
			ILLUSIONER_SPAWNS);

	private static final int[] ICEOLOGER_SPAWNS = new int[] { 0, 0, 0, 0, 0, 1, 1, 2 };
	public static final EnumProxy<Raid.RaiderType> ICEOLOGER = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) ModEntities.ICEOLOGER::get,
			ICEOLOGER_SPAWNS);

	private static final int[] WINDCALLER_SPAWNS = new int[] { 0, 0, 0, 0, 0, 1, 1, 2 };
	public static final EnumProxy<Raid.RaiderType> WINDCALLER = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) ModEntities.WINDCALLER::get,
			WINDCALLER_SPAWNS);

	private static final int[] SQUALL_GOLEM_SPAWNS = new int[] { 0, 0, 0, 1, 0, 1, 0, 2 };
	public static final EnumProxy<Raid.RaiderType> SQUALL_GOLEM = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) ModEntities.SQUALL_GOLEM::get,
			SQUALL_GOLEM_SPAWNS);

	private static final int[] REDSTONE_GOLEM_SPAWNS = new int[] { 0, 0, 0, 0, 0, 0, 0, 1 };
	public static final EnumProxy<Raid.RaiderType> REDSTONE_GOLEM = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) ModEntities.REDSTONE_GOLEM::get,
			REDSTONE_GOLEM_SPAWNS);

	private static final int[] REDSTONE_MONSTROSITY_SPAWNS = new int[] { 0, 0, 0, 0, 0, 0, 0, 1 };
	public static final EnumProxy<Raid.RaiderType> REDSTONE_MONSTROSITY = new EnumProxy<>(
			Raid.RaiderType.class,
			(Supplier<EntityType<? extends Raider>>) ModEntities.REDSTONE_MONSTROSITY::get,
			REDSTONE_MONSTROSITY_SPAWNS);

	public static void initWaveMemberEntries() {
		if (!DungeonsMobsConfig.COMMON.ENABLE_ARMORED_PILLAGERS_IN_RAIDS.get())
			Arrays.fill(ARMORED_PILLAGER_SPAWNS, 0);

		if (!DungeonsMobsConfig.COMMON.ENABLE_ARMORED_VINDICATORS_IN_RAIDS.get())
			Arrays.fill(ARMORED_VINDICATOR_SPAWNS, 0);

		if (!DungeonsMobsConfig.COMMON.ENABLE_MOUNTAINEERS_IN_RAIDS.get())
			Arrays.fill(MOUNTAINEER_SPAWNS, 0);

		if (!DungeonsMobsConfig.COMMON.ENABLE_ROYAL_GUARDS_IN_RAIDS.get())
			Arrays.fill(ROYAL_GUARD_SPAWNS, 0);

		if (!DungeonsMobsConfig.COMMON.ENABLE_GEOMANCERS_IN_RAIDS.get())
			Arrays.fill(GEOMANCER_SPAWNS, 0);

		if (!DungeonsMobsConfig.COMMON.ENABLE_MAGES_IN_RAIDS.get())
			Arrays.fill(MAGE_SPAWNS, 0);

		if (!DungeonsMobsConfig.COMMON.ENABLE_ILLUSIONERS_IN_RAIDS.get())
			Arrays.fill(ILLUSIONER_SPAWNS, 0);

		if (!DungeonsMobsConfig.COMMON.ENABLE_ICEOLOGERS_IN_RAIDS.get())
			Arrays.fill(ICEOLOGER_SPAWNS, 0);

		if (!DungeonsMobsConfig.COMMON.ENABLE_WINDCALLERS_IN_RAIDS.get())
			Arrays.fill(WINDCALLER_SPAWNS, 0);

		if (!DungeonsMobsConfig.COMMON.ENABLE_SQUALL_GOLEMS_IN_RAIDS.get())
			Arrays.fill(SQUALL_GOLEM_SPAWNS, 0);

		if (!DungeonsMobsConfig.COMMON.ENABLE_REDSTONE_GOLEMS_IN_RAIDS.get())
			Arrays.fill(REDSTONE_GOLEM_SPAWNS, 0);

		if (!DungeonsMobsConfig.COMMON.ENABLE_REDSTONE_MONSTROSITIES_IN_RAIDS.get())
			Arrays.fill(REDSTONE_MONSTROSITY_SPAWNS, 0);
	}
}
