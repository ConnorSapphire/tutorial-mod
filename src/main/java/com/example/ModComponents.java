package com.example;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static final ComponentType<Integer> CLICK_COUNT_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(ExampleMod.MOD_ID, "click_count"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static void initialize() {
        ExampleMod.LOGGER.info("Registering {} components", ExampleMod.MOD_ID);
        // Technically this method can stay empty, but some developers like to notify
        // the console, that certain parts of the mod have been successfully initialized
    }
}
