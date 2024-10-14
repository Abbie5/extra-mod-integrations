package com.kneelawk.extramodintegrations.tconstruct.recipe.entity;

import com.kneelawk.extramodintegrations.tconstruct.TiCCategories;
import com.kneelawk.extramodintegrations.util.stack.EntityEmiStack;
import com.kneelawk.extramodintegrations.util.widget.ScaledSlotWidget;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.recipe.modifiers.severing.SeveringRecipe;

import java.util.List;

public class SeveringEmiRecipe extends BasicEmiRecipe {
    public static final ResourceLocation BACKGROUND_LOC = TConstruct.getResource("textures/gui/jei/tinker_station.png");

    public SeveringEmiRecipe(SeveringRecipe recipe) {
        super(TiCCategories.SEVERING, recipe.getId(), 100, 38);

        this.inputs = List.of(
                EmiIngredient.of(recipe.getEntityInputs().stream().map(EntityEmiStack::new).toList()),
                EmiIngredient.of(recipe.getItemInputs().stream().map(EmiStack::of).toList())
        );
        this.outputs = List.of(EmiStack.of(recipe.getOutput()));
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(BACKGROUND_LOC, 0, 0, 100, 38, 0, 78);

        widgets.add(new ScaledSlotWidget(inputs.get(0), 1, 1, 2))
                .drawBack(false);

        widgets.addSlot(outputs.get(0), 71, 6)
                .drawBack(false)
                .large(true)
                .recipeContext(this);
    }

    @Override
    public boolean supportsRecipeTree() {
        return false;
    }
}
