package com.kneelawk.extramodintegrations;

import net.neoforged.fml.ModList;

import org.jetbrains.annotations.Nullable;

import com.kneelawk.extramodintegrations.util.ReflectionUtils;
import dev.emi.emi.api.EmiRegistry;

public abstract class AbstractPnCIntegration {
    @Nullable
    public static final AbstractPnCIntegration INSTANCE;
    private static final String modName = "PneumaticCraft";

    static {
        if (ModList.get().isLoaded("pneumaticcraft")) {
            INSTANCE =
                    ReflectionUtils.newIntegrationInstance(
                            "com.kneelawk.extramodintegrations.pnc.PnCIntegration", modName);
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
