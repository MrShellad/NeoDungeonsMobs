package net.firefoxsalesman.dungeonsmobs.mod;

import baguchi.enchantwithmob.EnchantWithMob;
import baguchi.enchantwithmob.mobenchant.MobEnchant;
import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.firefoxsalesman.dungeonsmobs.mobenchants.BurningMobEnchant;
import net.firefoxsalesman.dungeonsmobs.mobenchants.ChillingMobEnchant;
import net.firefoxsalesman.dungeonsmobs.mobenchants.CommittedMobEnchant;
import net.firefoxsalesman.dungeonsmobs.mobenchants.CriticalHitMobEnchant;
import net.firefoxsalesman.dungeonsmobs.mobenchants.DoubleDamageMobEnchant;
import net.firefoxsalesman.dungeonsmobs.mobenchants.EchoMobEnchant;
import net.firefoxsalesman.dungeonsmobs.mobenchants.GravityPulseMobEnchant;
import net.firefoxsalesman.dungeonsmobs.mobenchants.HealsAlliesMobEnchant;
import net.firefoxsalesman.dungeonsmobs.mobenchants.LeechingMobEnchant;
import net.firefoxsalesman.dungeonsmobs.mobenchants.RegenerationMobEnchant;
import net.firefoxsalesman.dungeonsmobs.mobenchants.WeakeningMobEnchant;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModMobEnchants {
	private static final DeferredRegister<MobEnchant> MOB_ENCHANTS_DEFERRED = DeferredRegister
			.create(ResourceLocation.fromNamespaceAndPath(EnchantWithMob.MODID, "mob_enchant"), DungeonsMobs.MOD_ID);
	public static final DeferredHolder<MobEnchant, BurningMobEnchant> BURNING = MOB_ENCHANTS_DEFERRED.register("burning",
			() -> new BurningMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.UNCOMMON, 3, 2)));
	public static final DeferredHolder<MobEnchant, ChillingMobEnchant> CHILLING = MOB_ENCHANTS_DEFERRED.register("chilling",
			() -> new ChillingMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.UNCOMMON, 3, 2)));
	public static final DeferredHolder<MobEnchant, CommittedMobEnchant> COMMITTED = MOB_ENCHANTS_DEFERRED.register("committed",
			() -> new CommittedMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.UNCOMMON, 3, 2)));
	public static final DeferredHolder<MobEnchant, CriticalHitMobEnchant> CRITICAL_HIT = MOB_ENCHANTS_DEFERRED.register(
			"critical_hit",
			() -> new CriticalHitMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.UNCOMMON, 3, 2)));
	public static final DeferredHolder<MobEnchant, DoubleDamageMobEnchant> DOUBLE_DAMAGE = MOB_ENCHANTS_DEFERRED.register(
			"double_damage",
			() -> new DoubleDamageMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.VERY_RARE, 1, 8)));
	public static final DeferredHolder<MobEnchant, EchoMobEnchant> ECHO = MOB_ENCHANTS_DEFERRED.register("echo",
			() -> new EchoMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.VERY_RARE, 3, 8)));
	public static final DeferredHolder<MobEnchant, GravityPulseMobEnchant> GRAVITY_PULSE = MOB_ENCHANTS_DEFERRED.register(
			"gravity_pulse",
			() -> new GravityPulseMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.RARE, 3, 4)));
	public static final DeferredHolder<MobEnchant, HealsAlliesMobEnchant> HEALS_ALLIES = MOB_ENCHANTS_DEFERRED.register(
			"heals_allies",
			() -> new HealsAlliesMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.RARE, 3, 4)));
	public static final DeferredHolder<MobEnchant, LeechingMobEnchant> LEECHING = MOB_ENCHANTS_DEFERRED.register("leeching",
			() -> new LeechingMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.UNCOMMON, 3, 2)));
	public static final DeferredHolder<MobEnchant, MobEnchant> RADIANCE = MOB_ENCHANTS_DEFERRED.register("radiance",
			() -> new MobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.RARE, 3, 4)));
	public static final DeferredHolder<MobEnchant, RegenerationMobEnchant> REGENERATION = MOB_ENCHANTS_DEFERRED.register(
			"regeneration",
			() -> new RegenerationMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.COMMON, 3, 1)));
	public static final DeferredHolder<MobEnchant, MobEnchant> RUSH = MOB_ENCHANTS_DEFERRED.register("rush",
			() -> new MobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.COMMON, 3, 1)));
	public static final DeferredHolder<MobEnchant, WeakeningMobEnchant> WEAKENING = MOB_ENCHANTS_DEFERRED.register("weakening",
			() -> new WeakeningMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.COMMON, 3, 1)));

	public static void register(IEventBus eventBus) {
		MOB_ENCHANTS_DEFERRED.register(eventBus);
	}
}
