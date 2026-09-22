package net.firefoxsalesman.dungeonsmobs.capabilities.ancient;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.BossEvent;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class Ancient implements INBTSerializable<CompoundTag> {
	private boolean ancient = false;
	private ServerBossEvent bossInfo = null;

	public boolean isAncient() {
		return ancient;
	}

	public void setAncient(boolean ancient) {
		this.ancient = ancient;
	}

	public boolean initiateBossBar(Component displayName) {
		bossInfo = new ServerBossEvent(displayName, BossEvent.BossBarColor.YELLOW,
				BossEvent.BossBarOverlay.PROGRESS);
		return true;
	}

	public ServerBossEvent getBossInfo() {
		return bossInfo;
	}

	@Override
	public CompoundTag serializeNBT(HolderLookup.Provider provider) {
		CompoundTag tag = new CompoundTag();
		tag.putBoolean("ancient", this.isAncient());
		if (this.getBossInfo() != null) {
			tag.putString("displayName", this.getBossInfo().getName().getString());
		}
		return tag;
	}

	@Override
	public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
		this.setAncient(tag.getBoolean("ancient"));
		if (tag.contains("displayName")) {
			this.initiateBossBar(Component.literal(tag.getString("displayName")));
		}
	}
}
