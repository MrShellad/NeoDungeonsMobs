package net.firefoxsalesman.dungeonsmobs.capabilities;

import java.util.function.Supplier;

import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.firefoxsalesman.dungeonsmobs.capabilities.ancient.Ancient;
import net.firefoxsalesman.dungeonsmobs.capabilities.convertible.Convertible;
import net.firefoxsalesman.dungeonsmobs.capabilities.properties.MobProps;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModCapabilities {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister
			.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, DungeonsMobs.MOD_ID);

	public static final Supplier<AttachmentType<Ancient>> ANCIENT = ATTACHMENT_TYPES.register("ancient",
			() -> AttachmentType.serializable(Ancient::new).build());
	public static final Supplier<AttachmentType<Convertible>> CONVERTIBLE = ATTACHMENT_TYPES.register("convertible",
			() -> AttachmentType.serializable(Convertible::new).build());
	public static final Supplier<AttachmentType<MobProps>> MOB_PROPS = ATTACHMENT_TYPES.register("mob_props",
			() -> AttachmentType.serializable(MobProps::new).build());

	public static void setupCapabilities() {
	}
}
