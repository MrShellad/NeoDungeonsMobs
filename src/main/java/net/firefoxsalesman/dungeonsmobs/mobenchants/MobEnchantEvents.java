package net.firefoxsalesman.dungeonsmobs.mobenchants;


import static net.firefoxsalesman.dungeonslibs.utils.AreaOfEffectHelper.applyToNearbyEntities;
import static net.firefoxsalesman.dungeonslibs.utils.AreaOfEffectHelper.getCanHealPredicate;
import static net.firefoxsalesman.dungeonsmobs.DungeonsMobs.PROXY;
import static net.firefoxsalesman.dungeonsmobs.mobenchants.NewMobEnchantUtils.executeIfPresentWithLevel;

import net.firefoxsalesman.dungeonslibs.utils.ModHelper;
import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.firefoxsalesman.dungeonsmobs.mod.ModMobEnchants;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class MobEnchantEvents {
	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent.Pre event) {
		if (ModHelper.hasMod("enchantwithmob")) {
			LivingEntity defender = event.getEntity();
			RushMobEnchant.doEffect(defender);
			HealsAlliesMobEnchant.doEffect(defender, event.getNewDamage());
			Entity attacker;
			if (!event.getSource().is(DamageTypeTags.IS_PROJECTILE)) {
				attacker = event.getSource().getDirectEntity();
			} else {
				attacker = event.getSource().getEntity();
			}
			if (attacker instanceof LivingEntity) {

				CommittedMobEnchant.doEffect(defender, (LivingEntity) attacker, event);
				CriticalHitMobEnchant.doEffect(defender, (LivingEntity) attacker, event);
				DoubleDamageMobEnchant.doEffect(defender, (LivingEntity) attacker, event);
				WeakeningMobEnchant.doEffect(defender, (LivingEntity) attacker);
				// radiance
				executeIfPresentWithLevel((LivingEntity) attacker, ModMobEnchants.RADIANCE.get(),
						(level) -> {
							LivingEntity source = event.getSource()
									.is(DamageTypeTags.IS_PROJECTILE)
											? event.getEntity()
											: (LivingEntity) attacker;
							applyToNearbyEntities(source, 1.5F,
									getCanHealPredicate(source),
									(LivingEntity nearbyEntity) -> {
										nearbyEntity.heal(level);
										PROXY.spawnParticles(nearbyEntity,
												ParticleTypes.HEART);
									});
						});
			}
		}
	}

	@SubscribeEvent
	public static void onLivingUpdate(EntityTickEvent.Pre event) {
		if (!(event.getEntity() instanceof LivingEntity entity)) return;
		if (ModHelper.hasMod("enchantwithmob")) {
			BurningMobEnchant.doEffect(entity);
			ChillingMobEnchant.doEffect(entity);
			GravityPulseMobEnchant.doEffect(entity);
			RegenerationMobEnchant.doEffect(entity);
		}
	}

	@SubscribeEvent
	public static void onLivingAttack(LivingIncomingDamageEvent event) {
		if (ModHelper.hasMod("enchantwithmob")) {
			LivingEntity defender = event.getEntity();
			Entity entity = event.getSource().getEntity();
			EchoMobEnchant.doEffect(defender, entity, event.getSource(), event.getAmount());
		}
	}

	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		if (ModHelper.hasMod("enchantwithmob")) {
			LivingEntity defender = event.getEntity();
			Entity entity = event.getSource().getEntity();
			if (entity instanceof LivingEntity attacker) {
				LeechingMobEnchant.doEffect(attacker, defender);
			}
		}
	}
}
