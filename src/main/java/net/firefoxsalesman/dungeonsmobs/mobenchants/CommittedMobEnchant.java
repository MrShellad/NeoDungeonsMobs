package net.firefoxsalesman.dungeonsmobs.mobenchants;

import baguchi.enchantwithmob.mobenchant.MobEnchant;
import net.firefoxsalesman.dungeonsmobs.mod.ModMobEnchants;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public class CommittedMobEnchant extends MobEnchant {
	public CommittedMobEnchant(Properties properties) {
		super(properties);
	}

	public static void doEffect(LivingEntity defender, LivingEntity attacker, LivingDamageEvent.Pre event) {
		NewMobEnchantUtils.executeIfPresentWithLevel(attacker, ModMobEnchants.COMMITTED.get(),
				(level) -> {
					if (defender.getHealth() >= defender.getMaxHealth())
						return;
					event.setNewDamage(event.getNewDamage() + (event.getNewDamage() * .25F * (level + 1)));

				});
	}
}
