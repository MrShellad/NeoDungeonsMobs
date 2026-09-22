package net.firefoxsalesman.dungeonsmobs.mobenchants;

import baguchi.enchantwithmob.mobenchant.MobEnchant;
import net.firefoxsalesman.dungeonsmobs.mod.ModMobEnchants;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public class DoubleDamageMobEnchant extends MobEnchant {
	public DoubleDamageMobEnchant(Properties properties) {
		super(properties);
	}

	public static void doEffect(LivingEntity defender, LivingEntity attacker, LivingDamageEvent.Pre event) {
		NewMobEnchantUtils.executeIfPresentWithLevel(attacker, ModMobEnchants.DOUBLE_DAMAGE.get(),
				(level) -> event.setNewDamage(event.getNewDamage() * 2));
	}
}
