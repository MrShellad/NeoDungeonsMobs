package net.firefoxsalesman.dungeonsmobs.compat.enchantwithmob;

import baguchi.enchantwithmob.api.IEnchantCap;
import baguchi.enchantwithmob.capability.MobEnchantCapability;
import baguchi.enchantwithmob.registry.MobEnchants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.Collection;
import java.util.function.Consumer;

public class AncientEnchantWithMobCompat {

	public static void addEnchant(LivingEntity entity, ResourceLocation enchant, boolean ancient) {
		MobEnchantCapability enchantCap = entity instanceof IEnchantCap enchantedEntity
				? enchantedEntity.getEnchantCap()
				: new MobEnchantCapability();
		MobEnchants.getRegistry().getHolder(enchant).ifPresent(holder -> {
			enchantCap.addMobEnchant(entity, holder, holder.value().getMaxLevel());
		});
	}

	public static Collection<ResourceLocation> getEnchantKeys() {
		return MobEnchants.getRegistry().keySet();
	}

	public static void collectEnchantKeys(LivingEntity entity, Consumer<ResourceLocation> consumer) {
		MobEnchantCapability enchantCap = entity instanceof IEnchantCap enchantedEntity
				? enchantedEntity.getEnchantCap()
				: new MobEnchantCapability();
		enchantCap.getMobEnchants().forEach(mobEnchantment -> {
			ResourceLocation key = mobEnchantment.getMobEnchant().unwrapKey().map(k -> k.location()).orElse(null);
			if (key != null) {
				consumer.accept(key);
			}
		});
	}
}
