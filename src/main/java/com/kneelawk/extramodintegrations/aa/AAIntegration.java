package com.kneelawk.extramodintegrations.aa;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeManager;

import com.kneelawk.extramodintegrations.AbstractAAIntegration;
import com.kneelawk.extramodintegrations.aa.recipe.CoffeeMachineEmiRecipe;
import com.kneelawk.extramodintegrations.aa.recipe.CrushingEmiRecipe;
import com.kneelawk.extramodintegrations.aa.recipe.EmpoweringEmiRecipe;
import com.kneelawk.extramodintegrations.aa.recipe.FermentingEmiRecipe;
import com.kneelawk.extramodintegrations.aa.recipe.LaserEmiRecipe;
import com.kneelawk.extramodintegrations.aa.recipe.MiningLensEmiRecipe;
import com.kneelawk.extramodintegrations.aa.recipe.PressingEmiRecipe;
import com.kneelawk.extramodintegrations.util.NamedEmiRecipeCategory;
import de.ellpeck.actuallyadditions.api.ActuallyAdditionsAPI;
import de.ellpeck.actuallyadditions.mod.blocks.ActuallyBlocks;
import de.ellpeck.actuallyadditions.mod.crafting.ActuallyRecipes;
import de.ellpeck.actuallyadditions.mod.items.ActuallyItems;
import de.ellpeck.actuallyadditions.mod.items.base.ItemEnergy;
import de.ellpeck.actuallyadditions.mod.util.CapHelper;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.stack.Comparison;
import dev.emi.emi.api.stack.EmiStack;

import java.util.Arrays;
import java.util.Optional;

import static de.ellpeck.actuallyadditions.mod.ActuallyAdditions.modLoc;

public class AAIntegration extends AbstractAAIntegration {
    public static final EmiRecipeCategory FERMENTING = new NamedEmiRecipeCategory(modLoc("fermenting"), EmiStack.of(ActuallyBlocks.FERMENTING_BARREL.getItem()), Component.literal("Fermenting Recipe"));
    public static final EmiRecipeCategory PRESSING = new NamedEmiRecipeCategory(modLoc("pressing"), EmiStack.of(ActuallyBlocks.CANOLA_PRESS.getItem()), Component.literal("Pressing Recipe"));
    public static final EmiRecipeCategory LASER = new NamedEmiRecipeCategory(modLoc("laser"), EmiStack.of(ActuallyBlocks.ATOMIC_RECONSTRUCTOR.getItem()), Component.translatable("container.actuallyadditions.reconstructor"));
    public static final EmiRecipeCategory EMPOWERER = new NamedEmiRecipeCategory(modLoc("empowerer"), EmiStack.of(ActuallyBlocks.EMPOWERER.getItem()), Component.translatable("container.actuallyadditions.empowerer"));
    public static final EmiRecipeCategory COFFEE_MACHINE = new NamedEmiRecipeCategory(modLoc("coffee_machine"), EmiStack.of(ActuallyBlocks.COFFEE_MACHINE.getItem()), Component.translatable("container.actuallyadditions.coffeeMachine"));
    public static final EmiRecipeCategory CRUSHING = new NamedEmiRecipeCategory(modLoc("crushing"), EmiStack.of(ActuallyBlocks.CRUSHER.getItem()), Component.translatable("container.actuallyadditions.crusher"));
    public static final EmiRecipeCategory MINING_LENS = new NamedEmiRecipeCategory(modLoc("mining_lens"), EmiStack.of(ActuallyItems.LENS_OF_THE_MINER.get()), Component.translatable("jei.actuallyadditions.mining_lens"));

    @Override
    protected void registerImpl(EmiRegistry registry) {
        registry.addCategory(COFFEE_MACHINE);
        registry.addCategory(CRUSHING);
        registry.addCategory(EMPOWERER);
        registry.addCategory(FERMENTING);
        registry.addCategory(LASER);
        registry.addCategory(MINING_LENS);
        registry.addCategory(PRESSING);

        registry.addWorkstation(VanillaEmiRecipeCategories.CRAFTING, EmiStack.of(ActuallyItems.CRAFTER_ON_A_STICK));
        registry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, EmiStack.of(ActuallyBlocks.POWERED_FURNACE.getItem()));
        registry.addWorkstation(FERMENTING, EmiStack.of(ActuallyBlocks.FERMENTING_BARREL.getItem()));
        registry.addWorkstation(LASER, EmiStack.of(ActuallyBlocks.ATOMIC_RECONSTRUCTOR.getItem()));
        registry.addWorkstation(EMPOWERER, EmiStack.of(ActuallyBlocks.EMPOWERER.getItem()));
        registry.addWorkstation(COFFEE_MACHINE, EmiStack.of(ActuallyBlocks.COFFEE_MACHINE.getItem()));
        registry.addWorkstation(PRESSING, EmiStack.of(ActuallyBlocks.CANOLA_PRESS.getItem()));
        registry.addWorkstation(CRUSHING, EmiStack.of(ActuallyBlocks.CRUSHER.getItem()));
        registry.addWorkstation(CRUSHING, EmiStack.of(ActuallyBlocks.CRUSHER_DOUBLE.getItem()));
        registry.addWorkstation(MINING_LENS, EmiStack.of(ActuallyItems.LENS_OF_THE_MINER.get()));

        RecipeManager manager = registry.getRecipeManager();
        manager.getAllRecipesFor(ActuallyRecipes.Types.FERMENTING.get())
                .stream()
                .map(FermentingEmiRecipe::new)
                .forEach(registry::addRecipe);
        manager.getAllRecipesFor(ActuallyRecipes.Types.LASER.get())
                .stream()
                .map(LaserEmiRecipe::new)
                .forEach(registry::addRecipe);
        manager.getAllRecipesFor(ActuallyRecipes.Types.EMPOWERING.get())
                .stream()
                .map(EmpoweringEmiRecipe::new)
                .forEach(registry::addRecipe);
        manager.getAllRecipesFor(ActuallyRecipes.Types.COFFEE_INGREDIENT.get())
                .stream()
                .map(CoffeeMachineEmiRecipe::new)
                .forEach(registry::addRecipe);
        manager.getAllRecipesFor(ActuallyRecipes.Types.PRESSING.get())
                .stream()
                .map(PressingEmiRecipe::new)
                .forEach(registry::addRecipe);
        manager.getAllRecipesFor(ActuallyRecipes.Types.CRUSHING.get())
                .stream()
                .map(CrushingEmiRecipe::new)
                .forEach(registry::addRecipe);
        manager.getAllRecipesFor(ActuallyRecipes.Types.MINING_LENS.get())
                .stream()
                .map(MiningLensEmiRecipe::new)
                .forEach(registry::addRecipe);

        Comparison energyComparison = Comparison.compareData(stack ->
                CapHelper.getEnergyStorage(stack.getItemStack())
                        .flatMap(storage ->
                                storage.getEnergyStored() == storage.getMaxEnergyStored() ? Optional.of("charged") : Optional.of("uncharged")
                        ).orElse("uncharged")
        );
        ActuallyItems.ITEMS.getEntries().forEach(entry -> {
            Item item = entry.get();
            if (item instanceof ItemEnergy) {
                registry.setDefaultComparison(item, energyComparison);
            }
        });
        registry.setDefaultComparison(ActuallyItems.COFFEE_CUP.get(), Comparison.compareData(stack -> {
            MobEffectInstance[] effects = ActuallyAdditionsAPI.methodHandler.getEffectsFromStack(stack.getItemStack());
            if (effects == null) return null;
            return Arrays.asList(effects);
        }));
    }
}
