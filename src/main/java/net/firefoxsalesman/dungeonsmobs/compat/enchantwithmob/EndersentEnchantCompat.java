package net.firefoxsalesman.dungeonsmobs.compat.enchantwithmob;

import baguchi.enchantwithmob.api.IEnchantCap;
import baguchi.enchantwithmob.capability.MobEnchantCapability;
import baguchi.enchantwithmob.mobenchant.MobEnchant;
import baguchi.enchantwithmob.registry.MobEnchants;
import net.firefoxsalesman.dungeonsmobs.entity.ender.EyeHolderEndersentEntity;
import net.firefoxsalesman.dungeonsmobs.mod.ModMobEnchants;
import net.minecraft.network.chat.Component;

import java.util.List;

public class EndersentEnchantCompat {

	public static void applyEnchants(EyeHolderEndersentEntity entity) {
		MobEnchantCapability cap = entity instanceof IEnchantCap enchantedEntity
				? enchantedEntity.getEnchantCap()
				: new MobEnchantCapability();
		if (!cap.hasEnchant()) {
			int type = entity.getRandom().nextInt(2);
			switch (type) {
				case 0:
					setupEnchants(entity, "Blight Eye", List.of(MobEnchants.POISON_CLOUD.get(),
							ModMobEnchants.WEAKENING.get()), cap);
					break;
				case 1:
					setupEnchants(entity, "Spiked Eye", List.of(MobEnchants.STRONG.get(),
							MobEnchants.THORN.get()), cap);
					break;
			}
		}
	}

	private static void setupEnchants(EyeHolderEndersentEntity entity, String name, List<MobEnchant> enchants, MobEnchantCapability cap) {
		entity.setCustomName(Component.literal(name));
		enchants.forEach(enchant -> {
			MobEnchants.getRegistry().getHolder(MobEnchants.getRegistry().getKey(enchant)).ifPresent(holder -> {
				cap.addMobEnchant(entity, holder, enchant.getMaxLevel());
			});
		});
	}
}
