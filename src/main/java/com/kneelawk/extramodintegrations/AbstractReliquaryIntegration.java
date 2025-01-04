package com.kneelawk.extramodintegrations;

import net.neoforged.fml.ModList;

import org.jetbrains.annotations.Nullable;

import com.kneelawk.extramodintegrations.util.ReflectionUtils;
import dev.emi.emi.api.EmiRegistry;

public abstract class AbstractReliquaryIntegration {
    @Nullable
    public static final AbstractReliquaryIntegration INSTANCE;
    private static final String modName = "Reliquary Reincarnations";

    static {
        if (ModList.get().isLoaded("reliquary")) {
            INSTANCE =
                    ReflectionUtils.newIntegrationInstance(
                            "com.kneelawk.extramodintegrations.reliquary.ReliquaryIntegration", modName);
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
