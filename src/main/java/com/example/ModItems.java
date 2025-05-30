package com.example;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Item SUSPICIOUS_SUBSTANCE = register(
            "suspicious_substance",
            SuspiciousSubstance::new,
            new Item.Settings()
    );
    public static final Item BOWL_OF_SEWAGE = register(
            "bowl_of_sewage",
            Item::new,
            new Item.Settings().food(ModFoodComponents.BOWL_OF_SEWAGE, ModFoodComponents.BOWL_OF_SEWAGE_EFFECT)
    );
    public static final Item SAPPHIRE_SWORD = register(
            "sapphire_sword",
            setting -> new Item(setting.sword(ModToolMaterials.SAPPHIRE_TOOL_MATERIAL, 4.0f, -2.8f)),
            new Item.Settings()
    );
    public static final Item SAPPHIRE_HELMET = register(
            "sapphire_helmet",
            settings -> new Item(settings.armor(ModArmorMaterial.SAPPHIRE_ARMOR_MATERIAL, EquipmentType.HELMET)),
            new Item.Settings().maxDamage(EquipmentType.HELMET.getMaxDamage(ModArmorMaterial.SAPPHIRE_BASE_DURABILITY))
    );
    public static final Item SAPPHIRE_CHESTPLATE = register("sapphire_chestplate",
            settings -> new Item(settings.armor(ModArmorMaterial.SAPPHIRE_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)),
            new Item.Settings().maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(ModArmorMaterial.SAPPHIRE_BASE_DURABILITY))
    );

    public static final Item SAPPHIRE_LEGGINGS = register(
            "sapphire_leggings",
            settings -> new Item(settings.armor(ModArmorMaterial.SAPPHIRE_ARMOR_MATERIAL, EquipmentType.LEGGINGS)),
            new Item.Settings().maxDamage(EquipmentType.LEGGINGS.getMaxDamage(ModArmorMaterial.SAPPHIRE_BASE_DURABILITY))
    );

    public static final Item SAPPHIRE_BOOTS = register(
            "sapphire_boots",
            settings -> new Item(settings.armor(ModArmorMaterial.SAPPHIRE_ARMOR_MATERIAL, EquipmentType.BOOTS)),
            new Item.Settings().maxDamage(EquipmentType.BOOTS.getMaxDamage(ModArmorMaterial.SAPPHIRE_BASE_DURABILITY))
    );

    // Item groups
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(ExampleMod.MOD_ID, "item_group"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.SAPPHIRE_SWORD))
            .displayName(Text.translatable("itemGroup.tutorial-mod"))
            .build();

    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our suspicious item to the ingredients group.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((itemGroup) -> itemGroup.add(ModItems.SAPPHIRE_HELMET));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((itemGroup) -> itemGroup.add(ModItems.SAPPHIRE_CHESTPLATE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((itemGroup) -> itemGroup.add(ModItems.SAPPHIRE_LEGGINGS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((itemGroup) -> itemGroup.add(ModItems.SAPPHIRE_BOOTS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register((itemGroup) -> itemGroup.add(ModItems.SAPPHIRE_SWORD));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(ModItems.BOWL_OF_SEWAGE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> itemGroup.add(ModItems.SUSPICIOUS_SUBSTANCE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((itemGroup) -> itemGroup.add(ModItems.SUSPICIOUS_SUBSTANCE));

        // Register the group.
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

// Register items to the custom item group.
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY)
                .register(itemGroup -> {
            itemGroup.add(ModItems.SUSPICIOUS_SUBSTANCE);
            itemGroup.add(ModItems.BOWL_OF_SEWAGE);
            itemGroup.add(ModItems.SAPPHIRE_SWORD);
            itemGroup.add(ModItems.SAPPHIRE_HELMET);
            itemGroup.add(ModItems.SAPPHIRE_BOOTS);
            itemGroup.add(ModItems.SAPPHIRE_LEGGINGS);
            itemGroup.add(ModItems.SAPPHIRE_CHESTPLATE);
            // ...
        });

        // Add the suspicious substance to the registry of fuels, with a burn time of 30 seconds.
        // Remember, Minecraft deals with logical based-time using ticks.
        // 20 ticks = 1 second.
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.SUSPICIOUS_SUBSTANCE, 30 * 20);
        });

        // Add the suspicious substance to the composting registry with a 70% chance of increasing the composter's level.
        CompostingChanceRegistry.INSTANCE.add(ModItems.SUSPICIOUS_SUBSTANCE, 0.7f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.SUSPICIOUS_SUBSTANCE, 1f);
    }

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ExampleMod.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }
}
