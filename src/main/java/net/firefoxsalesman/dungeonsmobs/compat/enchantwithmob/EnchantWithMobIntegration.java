package net.firefoxsalesman.dungeonsmobs.compat.enchantwithmob;

import net.firefoxsalesman.dungeonsmobs.mod.ModMobEnchants;
import net.firefoxsalesman.dungeonsmobs.mobenchants.MobEnchantEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;

public class EnchantWithMobIntegration {
	public static void init(IEventBus modEventBus) {
		ModMobEnchants.register(modEventBus);
		NeoForge.EVENT_BUS.register(MobEnchantEvents.class);
	}
}
