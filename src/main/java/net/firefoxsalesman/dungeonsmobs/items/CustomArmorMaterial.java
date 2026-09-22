package net.firefoxsalesman.dungeonsmobs.items;

import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class CustomArmorMaterial {
	public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, DungeonsMobs.MOD_ID);

	public static final DeferredHolder<ArmorMaterial, ArmorMaterial> PURE_NETHERITE = ARMOR_MATERIALS.register("pure_netherite", () -> new ArmorMaterial(
			Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 2);
				map.put(ArmorItem.Type.LEGGINGS, 5);
				map.put(ArmorItem.Type.CHESTPLATE, 6);
				map.put(ArmorItem.Type.HELMET, 2);
				map.put(ArmorItem.Type.BODY, 5);
			}),
			9,
			SoundEvents.ARMOR_EQUIP_NETHERITE,
			() -> Ingredient.of(Tags.Items.INGOTS_NETHERITE),
			List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(DungeonsMobs.MOD_ID, "pure_netherite"))),
			0.0F,
			0.0F
	));
}
