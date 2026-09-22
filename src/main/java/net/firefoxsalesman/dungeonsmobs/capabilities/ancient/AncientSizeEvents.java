package net.firefoxsalesman.dungeonsmobs.capabilities.ancient;

import net.neoforged.fml.common.EventBusSubscriber;

import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.bus.api.SubscribeEvent;

@EventBusSubscriber(modid = DungeonsMobs.MOD_ID)
public class AncientSizeEvents {
	@SubscribeEvent
	public static void onEntityEventSize(EntityEvent.Size event) {
		Entity entity = event.getEntity();

		Ancient cap = AncientHelper.getAncientCapability(entity);
		if (cap.isAncient()) {
			event.setNewSize(event.getNewSize().scale(1.2F));
		}
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onRenderLivingEventPre(
			RenderLivingEvent.Pre<?, ?> event) {
		final LivingEntity entity = event.getEntity();
		Ancient cap = AncientHelper.getAncientCapability(entity);
		if (cap.isAncient()) {
			event.getPoseStack().pushPose();
			event.getPoseStack().scale(1.1F, 1.1F, 1.1F);
		}
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onRenderLivingEventPost(
			RenderLivingEvent.Post<?, ?> event) {
		final LivingEntity entity = event.getEntity();
		Ancient cap = AncientHelper.getAncientCapability(entity);
		if (cap.isAncient()) {
			event.getPoseStack().popPose();
		}
	}
}
