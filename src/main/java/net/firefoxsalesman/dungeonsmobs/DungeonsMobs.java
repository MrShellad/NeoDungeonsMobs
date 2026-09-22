package net.firefoxsalesman.dungeonsmobs;

import java.util.List;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.firefoxsalesman.dungeonslibs.client.ClientProxy;
import net.firefoxsalesman.dungeonslibs.network.CommonProxy;
import net.firefoxsalesman.dungeonslibs.utils.ModHelper;
import net.firefoxsalesman.dungeonsmobs.capabilities.ModCapabilities;
import net.firefoxsalesman.dungeonsmobs.client.ModItemModelProperties;
import net.firefoxsalesman.dungeonsmobs.client.particle.ModParticleTypes;
import net.firefoxsalesman.dungeonsmobs.config.DungeonsMobsConfig;
import net.firefoxsalesman.dungeonsmobs.entity.ModEntities;
import net.firefoxsalesman.dungeonsmobs.mod.ModEffects;
import net.firefoxsalesman.dungeonsmobs.mod.ModItems;
import net.firefoxsalesman.dungeonsmobs.mod.ModStructureModifiers;
import net.firefoxsalesman.dungeonsmobs.network.NetworkHandler;
import net.firefoxsalesman.dungeonsmobs.utils.GeneralHelper;
import net.firefoxsalesman.dungeonsmobs.worldgen.EntitySpawnPlacement;
import net.firefoxsalesman.dungeonsmobs.worldgen.RaidEntries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(DungeonsMobs.MOD_ID)
public class DungeonsMobs {
	public static final String MOD_ID = "dungeonsmobs";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static CommonProxy PROXY;

	public DungeonsMobs(IEventBus modEventBus, ModContainer modContainer) {
		PROXY = FMLEnvironment.dist.isClient() ? new ClientProxy() : new CommonProxy();
		modContainer.registerConfig(Type.COMMON, DungeonsMobsConfig.COMMON_SPEC,
				"dungeons-mobs-common.toml");
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);

		ModSoundEvents.register(modEventBus);
		ModEffects.register(modEventBus);

		modEventBus.addListener(this::commonSetup);
		ModEntities.register(modEventBus);
		ModItems.register(modEventBus);
		ModParticleTypes.register(modEventBus);

		ModCapabilities.ATTACHMENT_TYPES.register(modEventBus);

		if (ModHelper.hasMod("enchantwithmob")) {
			net.firefoxsalesman.dungeonsmobs.compat.enchantwithmob.EnchantWithMobIntegration.init(modEventBus);
		}

		NeoForge.EVENT_BUS.register(this);
		modEventBus.addListener(this::addCreative);
		modEventBus.addListener(EntitySpawnPlacement::onRegisterSpawnPlacements);
		ModStructureModifiers.register(modEventBus);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
	}

	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.COMBAT) {
			ModItems.getEntries().forEach(item -> event.accept(item.get()));
		}
		if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
			ModEntities.getEntries().forEach(item -> {
				boolean idMatches = false;
				for (String s : List.of("wraith", "necromancer"))
					if (GeneralHelper.modLoc(s + "_spawn_egg").equals(item.getId()))
						idMatches = true;
				if (!(ModHelper.hasGoety() && idMatches))
					event.accept(item.get());
			});
	}

	private void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(NetworkHandler::init);
		event.enqueueWork(RaidEntries::initWaveMemberEntries);
	}

	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		event.enqueueWork(ModItemModelProperties::registerProperties);
	}

	@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
		}
	}
}
