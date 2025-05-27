package com.example;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

public class ModToolMaterials {
    public static final ToolMaterial SAPPHIRE_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            1250,
            12.0f,
            3.0f,
            7,
            ItemTags.REPAIRS_IRON_ARMOR
            //SapphireArmorMaterial.REPAIRS_SAPPHIRE_ARMOR
    );
}
