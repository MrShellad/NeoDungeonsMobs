package net.firefoxsalesman.dungeonsmobs.utils;

import com.google.common.collect.Lists;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ModProjectileHelper {

	private static final double RAYTRACE_DISTANCE = 16.0D;

	public static InteractionHand getHandWith(LivingEntity livingEntity, Predicate<Item> itemPredicate) {

		return itemPredicate.test(livingEntity.getMainHandItem().getItem()) ? InteractionHand.MAIN_HAND
				: InteractionHand.OFF_HAND;
	}

	public static ItemStack createRocket(int explosions, DyeColor... dyeColor) {
		ItemStack rocket = new ItemStack(Items.FIREWORK_ROCKET);
		it.unimi.dsi.fastutil.ints.IntArrayList colors = new it.unimi.dsi.fastutil.ints.IntArrayList();
		for (DyeColor color : dyeColor) {
			colors.add(color.getFireworkColor());
		}
		net.minecraft.world.item.component.FireworkExplosion explosion = new net.minecraft.world.item.component.FireworkExplosion(
				net.minecraft.world.item.component.FireworkExplosion.Shape.BURST,
				colors,
				colors,
				false,
				false
		);
		List<net.minecraft.world.item.component.FireworkExplosion> explosionList = new ArrayList<>();
		for (int i = 0; i < explosions; i++) {
			explosionList.add(explosion);
		}
		rocket.set(net.minecraft.core.component.DataComponents.FIREWORKS,
				new net.minecraft.world.item.component.Fireworks(1, explosionList));
		return rocket;
	}

	public static HitResult getLaserRayTrace(LivingEntity shooter) {
		Level world = shooter.level();
		BlockHitResult blockRTR = (BlockHitResult) shooter.pick(RAYTRACE_DISTANCE, 1.0F, false);
		Vec3 startVec = shooter.getEyePosition(1.0F);
		Vec3 lookVec = shooter.getViewVector(1.0F);
		Vec3 endVec = startVec.add(lookVec.x * RAYTRACE_DISTANCE, lookVec.y * RAYTRACE_DISTANCE,
				lookVec.z * RAYTRACE_DISTANCE);
		if (blockRTR.getType() != HitResult.Type.MISS)
			endVec = blockRTR.getLocation();

		AABB targetAreaBoundingBox = shooter.getBoundingBox().expandTowards(lookVec.scale(RAYTRACE_DISTANCE))
				.inflate(1.0D);
		EntityHitResult entityRTR = ProjectileUtil.getEntityHitResult(world, shooter, startVec, endVec,
				targetAreaBoundingBox, entity -> !entity.isSpectator() && entity.isPickable());

		if (entityRTR != null) {
			return entityRTR;
		} else {
			return blockRTR;
		}
	}
}
