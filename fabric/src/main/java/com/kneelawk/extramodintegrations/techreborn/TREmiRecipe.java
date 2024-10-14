package com.kneelawk.extramodintegrations.techreborn;

import com.kneelawk.extramodintegrations.ExMIMod;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;
import reborncore.common.crafting.RebornRecipe;

import java.util.List;

public abstract class TREmiRecipe<R extends RebornRecipe> implements EmiRecipe {
    protected final R recipe;
    protected final @Nullable ResourceLocation id;
    protected final List<EmiIngredient> inputs;
    protected final List<EmiStack> outputs;

    public TREmiRecipe(RecipeHolder<? extends R> holder) {
        this.recipe = holder.value();
        id = holder.id();
        inputs = recipe.ingredients().stream().map(ing -> EmiIngredient.of(ing.ingredient(), ing.count()))
            .toList();
        outputs = recipe.outputs().stream().map(EmiStack::of).toList();
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return outputs;
    }

    protected final void checkInputCount(int count) {
        if (inputs.size() != count) {
            ExMIMod.LOGGER.warn("Expected recipe {} ({}) to have {} inputs but instead it has {}", id, recipe, count,
                inputs.size());
        }
    }

    protected final void checkOutputCount(int count) {
        if (outputs.size() != count) {
            ExMIMod.LOGGER.warn("Expected recipe {} ({}) to have {} outputs but instead it has {}", id, recipe, count,
                outputs.size());
        }
    }

    protected EmiIngredient getInput(int index) {
        if (index >= inputs.size()) {
            return EmiStack.EMPTY;
        } else {
            return inputs.get(index);
        }
    }

    protected EmiStack getOutput(int index) {
        if (index >= outputs.size()) {
            return EmiStack.EMPTY;
        } else {
            return outputs.get(index);
        }
    }
}
