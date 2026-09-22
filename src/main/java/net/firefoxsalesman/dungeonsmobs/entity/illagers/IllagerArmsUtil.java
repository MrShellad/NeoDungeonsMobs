package net.firefoxsalesman.dungeonsmobs.entity.illagers;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public class IllagerArmsUtil {
    public static boolean armorHasCrossedArms(AbstractIllager p_241739_3_, ItemStack itemstack) {
        return !(itemstack.getItem() instanceof ArmorItem) || resourceExists(getArmorResourceStatic(p_241739_3_, itemstack, EquipmentSlot.CHEST));
    }

    public static ResourceLocation getArmorResourceStatic(Entity entity, ItemStack stack, EquipmentSlot slot) {
        return getArmorResourceStatic(entity, stack, slot, "crossed");
    }

    public static ResourceLocation getArmorResourceStatic(Entity entity, ItemStack stack, EquipmentSlot slot, String type) {
        if (!(stack.getItem() instanceof ArmorItem item)) {
            return ResourceLocation.withDefaultNamespace("empty");
        }
        ResourceLocation materialLoc = item.getMaterial().unwrapKey().map(k -> k.location()).orElse(ResourceLocation.withDefaultNamespace("iron"));
        String suffix = (type == null || type.isEmpty()) ? "" : "_" + type;
        return ResourceLocation.fromNamespaceAndPath(materialLoc.getNamespace(), String.format("textures/models/armor/%s_layer_1%s.png", materialLoc.getPath(), suffix));
    }

    public static boolean resourceExists(ResourceLocation resourceLocation) {
        if (resourceLocation != null) {
            return Minecraft.getInstance().getResourceManager().getResource(resourceLocation).isPresent();
        }
        return false;
    }
}
