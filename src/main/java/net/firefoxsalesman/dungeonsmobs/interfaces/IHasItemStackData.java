package net.firefoxsalesman.dungeonsmobs.interfaces;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public interface IHasItemStackData {

	ItemStack getDataItem();

	void setDataItem(ItemStack dataItem);

	default void writeDataItem(CompoundTag tag, String key) {
		if (this instanceof Entity entity) {
			writeDataItem(entity.registryAccess(), tag, key);
		}
	}

	default void readDataItem(CompoundTag tag, String key) {
		if (this instanceof Entity entity) {
			readDataItem(entity.registryAccess(), tag, key);
		}
	}

	default void writeDataItem(HolderLookup.Provider provider, CompoundTag tag, String key) {
		ItemStack itemStack = this.getDataItem();
		if (!itemStack.isEmpty()) {
			tag.put(key, itemStack.save(provider));
		}
	}

	default void readDataItem(HolderLookup.Provider provider, CompoundTag tag, String key) {
		if (tag.contains(key, 10)) {
			ItemStack itemstack = ItemStack.parseOptional(provider, tag.getCompound(key));
			this.setDataItem(itemstack);
		}
	}
}
