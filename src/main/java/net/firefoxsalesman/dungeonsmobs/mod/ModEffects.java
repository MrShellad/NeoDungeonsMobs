package net.firefoxsalesman.dungeonsmobs.mod;

import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.firefoxsalesman.dungeonsmobs.effects.WarpedEffect;
import net.firefoxsalesman.dungeonsmobs.entity.projectiles.BlueNethershroomEntity;
import net.firefoxsalesman.dungeonsmobs.effects.EnsnaredEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.resources.ResourceLocation;

public class ModEffects {

	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT,
			DungeonsMobs.MOD_ID);

	public static final DeferredHolder<MobEffect, MobEffect> ENSNARED = EFFECTS.register("ensnared",
			() -> new EnsnaredEffect(MobEffectCategory.HARMFUL, 0xdbe64e).addAttributeModifier(
					Attributes.MOVEMENT_SPEED,
					ResourceLocation.fromNamespaceAndPath(DungeonsMobs.MOD_ID, "effect.ensnared"), -5.0D,
					AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

	public static final DeferredHolder<MobEffect, MobEffect> WARPED = EFFECTS.register("warped",
			() -> new WarpedEffect(MobEffectCategory.HARMFUL,
					BlueNethershroomEntity.LIGHT_BLUE_HEX_COLOR_CODE));

	public static void register(IEventBus eventBus) {
		EFFECTS.register(eventBus);
	}
}
