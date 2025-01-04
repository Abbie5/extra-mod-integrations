package com.kneelawk.extramodintegrations;

import net.neoforged.fml.ModList;

import org.jetbrains.annotations.Nullable;

import com.kneelawk.extramodintegrations.util.ReflectionUtils;
import dev.emi.emi.api.EmiRegistry;

public abstract class AbstractArsIntegration {
    @Nullable
    public static final AbstractArsIntegration INSTANCE;
    private static final String modName = "Ars Nouveau";

    static {
        if (ModList.get().isLoaded("ars_nouveau")) {
            INSTANCE =
                    ReflectionUtils.newIntegrationInstance(
                            "com.kneelawk.extramodintegrations.ars.ArsIntegration", modName);
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
