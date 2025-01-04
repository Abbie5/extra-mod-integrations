/*
 * Copyright (c) 2020 vectorwing, the Farmer's Delight Refabricated authors
 * License available at https://github.com/MehVahdJukaar/FarmersDelightRefabricated/blob/b6690b2106abc6205021e40f9117c03f36323362/LICENSE
 */

package com.kneelawk.extramodintegrations.farmersdelight;

import com.kneelawk.extramodintegrations.util.NamedEmiRecipeCategory;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiRenderable;
import net.minecraft.resources.ResourceLocation;
import vectorwing.farmersdelight.common.utility.TextUtils;

import static com.kneelawk.extramodintegrations.farmersdelight.FDIntegration.res;

public class FDRecipeCategories {
    private static final ResourceLocation SIMPLIFIED_TEXTURES = res("textures/gui/emi/simplified.png");

    public static final EmiRecipeCategory COOKING = new NamedEmiRecipeCategory(res("cooking"), FDRecipeWorkstations.COOKING_POT, simplifiedRenderer(0, 0), TextUtils.getTranslation("jei.cooking"));
    public static final EmiRecipeCategory CUTTING = new NamedEmiRecipeCategory(res("cutting"), FDRecipeWorkstations.CUTTING_BOARD, simplifiedRenderer(16, 0), TextUtils.getTranslation("jei.cutting"));
    public static final EmiRecipeCategory DECOMPOSITION = new NamedEmiRecipeCategory(res("decomposition"), FDRecipeWorkstations.ORGANIC_COMPOST, simplifiedRenderer(32, 0), TextUtils.getTranslation("jei.decomposition"));

    private static EmiRenderable simplifiedRenderer(int u, int v) {
        return (draw, x, y, delta) -> {
            draw.blit(SIMPLIFIED_TEXTURES, x, y, u, v, 16, 16, 48, 16);
        };
    }
}
