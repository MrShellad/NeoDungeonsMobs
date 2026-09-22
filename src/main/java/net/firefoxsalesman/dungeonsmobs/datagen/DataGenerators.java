package net.firefoxsalesman.dungeonsmobs.datagen;

import net.neoforged.fml.common.EventBusSubscriber;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.SubscribeEvent;

import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;

@EventBusSubscriber(modid = DungeonsMobs.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		generator.addProvider(event.includeClient(),
				new ModItemModelProvider(packOutput, event.getExistingFileHelper()));
	}
}
