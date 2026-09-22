package net.firefoxsalesman.dungeonsmobs.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

import net.firefoxsalesman.dungeonsmobs.ModSoundEvents;
import net.firefoxsalesman.dungeonsmobs.interfaces.IHasInventorySprite;
import net.firefoxsalesman.dungeonslibs.capabilities.minionmaster.Leader;
import net.firefoxsalesman.dungeonslibs.items.artifacts.ArtifactItem;
import net.firefoxsalesman.dungeonslibs.items.artifacts.ArtifactUseContext;
import net.firefoxsalesman.dungeonslibs.items.interfaces.ISoulConsumer;
import net.firefoxsalesman.dungeonslibs.network.BreakItemMessage;
import net.firefoxsalesman.dungeonslibs.summon.SummonHelper;
import net.firefoxsalesman.dungeonslibs.utils.SoundHelper;
import net.firefoxsalesman.dungeonslibs.network.NetworkHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static net.firefoxsalesman.dungeonslibs.attribute.AttributeRegistry.SUMMON_CAP;
import static net.firefoxsalesman.dungeonslibs.capabilities.minionmaster.FollowerLeaderHelper.getLeaderCapability;

public class NecromancerStaffItem extends ArtifactItem implements IHasInventorySprite, ISoulConsumer {
	public NecromancerStaffItem(Properties properties) {
		super(properties);
		procOnItemUse = true;
	}

	public InteractionResultHolder<ItemStack> procArtifact(ArtifactUseContext itemUseContext) {
		Level world = itemUseContext.getLevel();
		if (world.isClientSide || itemUseContext.isHitMiss()) {
			return InteractionResultHolder.success(itemUseContext.getItemStack());
		} else {
			ItemStack itemUseContextItem = itemUseContext.getItemStack();
			Player itemUseContextPlayer = itemUseContext.getPlayer();
			BlockPos itemUseContextPos = itemUseContext.getClickedPos();
			Direction itemUseContextFace = itemUseContext.getClickedFace();
			BlockState blockState = world.getBlockState(itemUseContextPos);

			BlockPos blockPos;
			if (blockState.getCollisionShape(world, itemUseContextPos).isEmpty()) {
				blockPos = itemUseContextPos;
			} else {
				blockPos = itemUseContextPos.relative(itemUseContextFace);
			}

			if (itemUseContextPlayer != null) {
				Leader summonerCap = getLeaderCapability(itemUseContextPlayer);
				if (summonerCap != null) {
					Entity summoned = SummonHelper.summonEntity(itemUseContextPlayer,
							itemUseContextPlayer.blockPosition(), EntityType.ZOMBIE);
					if (summoned != null) {
						SoundHelper.playCreatureSound(itemUseContextPlayer,
								ModSoundEvents.NECROMANCER_SUMMON.get());
						itemUseContextItem.hurtAndBreak(1, itemUseContextPlayer, net.minecraft.world.entity.EquipmentSlot.MAINHAND);
						ArtifactItem.putArtifactOnCooldown(itemUseContextPlayer,
								itemUseContextItem.getItem());
					} else {
						if (world instanceof ServerLevel) {
							List<Entity> zombieEntities = summonerCap.getSummonedMobs()
									.stream()
									.filter(entity -> entity
											.getType() == EntityType.ZOMBIE)
									.collect(Collectors.toList());
							zombieEntities.forEach(entity -> {
								entity.teleportTo(
										(double) blockPos.getX() + 0.5D,
										(double) blockPos.getY() + 0.05D,
										(double) blockPos.getZ() + 0.5D);
							});
						}
					}
				}
			}
			return InteractionResultHolder.consume(itemUseContextItem);
		}
	}

	@Override
	public int getCooldownInSeconds() {
		return 20;
	}

	@Override
	public int getDurationInSeconds() {
		return 0;
	}

	@Override
	public Multimap<net.minecraft.core.Holder<Attribute>, AttributeModifier> getAttributeModifiers(top.theillusivec4.curios.api.SlotContext slotContext,
			net.minecraft.resources.ResourceLocation id, ItemStack stack) {
		Multimap<net.minecraft.core.Holder<Attribute>, AttributeModifier> modifiers = com.google.common.collect.HashMultimap.create(super.getAttributeModifiers(slotContext, id, stack));
		modifiers.put(SUMMON_CAP, new AttributeModifier(
				net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(net.firefoxsalesman.dungeonsmobs.DungeonsMobs.MOD_ID, "artifact_summon_cap_" + slotContext.index()),
				3, AttributeModifier.Operation.ADD_VALUE));
		return modifiers;
	}

	@Override
	public float getActivationCost(ItemStack stack) {
		return 50;
	}
}
