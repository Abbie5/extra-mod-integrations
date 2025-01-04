package com.kneelawk.extramodintegrations;

import net.neoforged.fml.ModList;

import org.jetbrains.annotations.Nullable;

import com.kneelawk.extramodintegrations.util.ReflectionUtils;
import dev.emi.emi.api.EmiRegistry;

public abstract class AbstractApotheosisIntegration {
    @Nullable
    public static final AbstractApotheosisIntegration INSTANCE;
    private static final String modName = "Apotheosis";

    static {
        if (ModList.get().isLoaded("apotheosis")) {
            INSTANCE =
                    ReflectionUtils.newIntegrationInstance(
                            "com.kneelawk.extramodintegrations.apotheosis.ApotheosisIntegration", modName);
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
