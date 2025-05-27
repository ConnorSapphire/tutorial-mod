package com.example;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.Map;

import static net.minecraft.registry.tag.ItemTags.REPAIRS_IRON_ARMOR;

public class ModArmorMaterial {
    public static final int SAPPHIRE_BASE_DURABILITY = 15;
    public static final RegistryKey<EquipmentAsset> SAPPHIRE_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(ExampleMod.MOD_ID, "sapphire"));

    public static final ArmorMaterial SAPPHIRE_ARMOR_MATERIAL = new ArmorMaterial(
            SAPPHIRE_BASE_DURABILITY,
            Map.of(
                    EquipmentType.HELMET, 3,
                    EquipmentType.CHESTPLATE, 8,
                    EquipmentType.LEGGINGS, 6,
                    EquipmentType.BOOTS, 3
            ),
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            0.0F,
            0.0F,
            REPAIRS_IRON_ARMOR,
            SAPPHIRE_ARMOR_MATERIAL_KEY
    );
}
