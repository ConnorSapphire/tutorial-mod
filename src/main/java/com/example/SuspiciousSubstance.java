package com.example;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class SuspiciousSubstance extends Item {
    public SuspiciousSubstance(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext itemUsageContext) {
        // Ensure we don't spawn the lightning only on the client.
        // This is to prevent desync.
        if (itemUsageContext.getWorld().isClient) {
            return ActionResult.PASS;
        }

        BlockPos blockPos = itemUsageContext.getBlockPos();

        // Spawn the lightning bolt.
        LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, itemUsageContext.getWorld());
        lightningBolt.setPosition(blockPos.toCenterPos());
        itemUsageContext.getWorld().spawnEntity(lightningBolt);

        return ActionResult.SUCCESS;
    }
}
