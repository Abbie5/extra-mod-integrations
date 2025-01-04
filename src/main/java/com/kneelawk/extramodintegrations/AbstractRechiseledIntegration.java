package com.kneelawk.extramodintegrations;

import net.neoforged.fml.ModList;

import org.jetbrains.annotations.Nullable;

import com.kneelawk.extramodintegrations.util.ReflectionUtils;
import dev.emi.emi.api.EmiRegistry;

public abstract class AbstractRechiseledIntegration {
    @Nullable
    public static final AbstractRechiseledIntegration INSTANCE;
    private static final String modName = "Rechiseled";

    static {
        if (ModList.get().isLoaded("rechiseled")) {
            INSTANCE =
                    ReflectionUtils.newIntegrationInstance(
                            "com.kneelawk.extramodintegrations.rechiseled.RechiseledIntegration", modName);
        } else {
            INSTANCE = null;
        }
    }

    protected abstract void registerImpl(EmiRegistry registry);

    public static void register(EmiRegistry registry) {
        if (INSTANCE != null) {
            INSTANCE.registerImpl(registry);
        } else {
            ExMIMod.logSkipping(modName);
        }
    }
}
