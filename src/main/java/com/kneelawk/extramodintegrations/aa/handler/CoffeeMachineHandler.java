package com.kneelawk.extramodintegrations.aa.handler;

import net.minecraft.world.inventory.Slot;

import com.kneelawk.extramodintegrations.aa.AAIntegration;
import de.ellpeck.actuallyadditions.mod.inventory.ContainerCoffeeMachine;
import de.ellpeck.actuallyadditions.mod.network.PacketHelperClient;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.handler.EmiCraftContext;
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachineHandler implements StandardRecipeHandler<ContainerCoffeeMachine> {
    @Override
    public List<Slot> getInputSources(ContainerCoffeeMachine handler) {
        List<Slot> list = new ArrayList<>();
        list.addAll(getCraftingSlots(handler));
        list.addAll(handler.slots.subList(11, 11 + 4 * 9));
        return list;
    }

    @Override
    public List<Slot> getCraftingSlots(ContainerCoffeeMachine handler) {
        return List.of(
                handler.slots.get(0),
                handler.slots.get(3),
                handler.slots.get(1)
        );
    }

    @Override
    public boolean craft(EmiRecipe recipe, EmiCraftContext<ContainerCoffeeMachine> context) {
        if (StandardRecipeHandler.super.craft(recipe, context)) {
            PacketHelperClient.sendButtonPacket(context.getScreenHandler().machine, 0);
            return true;
        }
        return false;
    }

    @Override
    public boolean supportsRecipe(EmiRecipe recipe) {
        return recipe.getCategory() == AAIntegration.COFFEE_MACHINE && recipe.supportsRecipeTree();
    }
}
