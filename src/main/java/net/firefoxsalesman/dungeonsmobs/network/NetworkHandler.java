package net.firefoxsalesman.dungeonsmobs.network;

import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.firefoxsalesman.dungeonsmobs.network.message.AncientMessage;
import net.firefoxsalesman.dungeonsmobs.network.message.BossBarMessage;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = DungeonsMobs.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkHandler {

	public static void init() {
	}

	@SubscribeEvent
	public static void register(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar("1");
		registrar.playToClient(
				AncientMessage.TYPE,
				AncientMessage.STREAM_CODEC,
				AncientMessage::handle);
		registrar.playToClient(
				BossBarMessage.TYPE,
				BossBarMessage.STREAM_CODEC,
				BossBarMessage::handle);
	}
}
