package net.firefoxsalesman.dungeonsmobs.client.particle;

import net.firefoxsalesman.dungeonsmobs.DungeonsMobs;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister
			.create(BuiltInRegistries.PARTICLE_TYPE, DungeonsMobs.MOD_ID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> REDSTONE_SPARK = registerParticle("redstone_spark");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DUST = registerParticle("dust");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WIND = registerParticle("wind");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> NECROMANCY = registerParticle("necromancy");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CORRUPTED_DUST = registerParticle("corrupted_dust");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CORRUPTED_MAGIC = registerParticle("corrupted_magic");

	private static DeferredHolder<ParticleType<?>, SimpleParticleType> registerParticle(String name) {
		return PARTICLES.register(name, () -> new SimpleParticleType(true));
	}

	public static void register(IEventBus eventBus) {
		PARTICLES.register(eventBus);
	}
}
