package net.firefoxsalesman.dungeonsmobs.network.message;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.firefoxsalesman.dungeonsmobs.client.renderer.BossBarRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record BossBarMessage(UUID bar, int boss, boolean remove) implements CustomPacketPayload {
	public static final Type<BossBarMessage> TYPE = new Type<>(
			ResourceLocation.fromNamespaceAndPath(DungeonsMobs.MOD_ID, "boss_bar_message"));

	public static final StreamCodec<ByteBuf, BossBarMessage> STREAM_CODEC = StreamCodec.composite(
			UUIDUtil.STREAM_CODEC, BossBarMessage::bar,
			ByteBufCodecs.VAR_INT, BossBarMessage::boss,
			ByteBufCodecs.BOOL, BossBarMessage::remove,
			BossBarMessage::new);

	public BossBarMessage(UUID bar, Mob boss, boolean remove) {
		this(bar, boss.getId(), remove);
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static void handle(BossBarMessage message, IPayloadContext context) {
		context.enqueueWork(() -> {
			Player player = Minecraft.getInstance().player;
			if (player != null) {
				Entity boss = player.level().getEntity(message.boss());
				if (boss instanceof Mob mob) {
					if (message.remove()) {
						BossBarRenderer.removeBossBar(message.bar(), mob);
					} else {
						BossBarRenderer.addBossBar(message.bar(), mob);
					}
				}
			}
		});
	}
}
