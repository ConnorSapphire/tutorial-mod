package com.example;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CounterItem extends Item {
    public CounterItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // Don't do anything on the client
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }

        // Read the current count and increase it by one
        int count = stack.getOrDefault(ModComponents.CLICK_COUNT_COMPONENT, 0);
        stack.set(ModComponents.CLICK_COUNT_COMPONENT, ++count);

        return ActionResult.SUCCESS;
    }
}
