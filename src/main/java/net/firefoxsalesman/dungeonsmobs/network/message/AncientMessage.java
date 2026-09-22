package net.firefoxsalesman.dungeonsmobs.network.message;

import io.netty.buffer.ByteBuf;
import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.firefoxsalesman.dungeonsmobs.capabilities.ancient.Ancient;
import net.firefoxsalesman.dungeonsmobs.capabilities.ancient.AncientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record AncientMessage(int entityId, boolean ancient) implements CustomPacketPayload {
	public static final Type<AncientMessage> TYPE = new Type<>(
			ResourceLocation.fromNamespaceAndPath(DungeonsMobs.MOD_ID, "ancient_message"));

	public static final StreamCodec<ByteBuf, AncientMessage> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.VAR_INT, AncientMessage::entityId,
			ByteBufCodecs.BOOL, AncientMessage::ancient,
			AncientMessage::new);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(AncientMessage message, IPayloadContext context) {
		context.enqueueWork(() -> {
			Entity entity = Minecraft.getInstance().player.level().getEntity(message.entityId());
			if (entity instanceof LivingEntity) {
				Ancient cap = AncientHelper.getAncientCapability(entity);
				cap.setAncient(message.ancient());
				entity.refreshDimensions();
			}
		});
	}
}
