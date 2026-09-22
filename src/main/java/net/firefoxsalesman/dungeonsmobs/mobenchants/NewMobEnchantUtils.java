package net.firefoxsalesman.dungeonsmobs.mobenchants;

import java.util.function.Consumer;
import baguchi.enchantwithmob.api.IEnchantCap;
import baguchi.enchantwithmob.capability.MobEnchantHandler;
import baguchi.enchantwithmob.mobenchant.MobEnchant;
import net.minecraft.world.entity.LivingEntity;

public class NewMobEnchantUtils {
	public static void executeIfPresentWithLevel(LivingEntity entity, MobEnchant mobEnchantment,
			Consumer<Integer> consumer) {
		if (entity instanceof IEnchantCap cap && cap.getEnchantCap() != null) {
			for (MobEnchantHandler handler : cap.getEnchantCap().getMobEnchants()) {
				if (handler.getMobEnchant() != null && handler.getMobEnchant().value() == mobEnchantment) {
					if (handler.getEnchantLevel() > 0) {
						consumer.accept(handler.getEnchantLevel());
					}
					return;
				}
			}
		}
	}
}
