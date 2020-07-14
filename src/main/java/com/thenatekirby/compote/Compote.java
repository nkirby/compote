package com.thenatekirby.compote;

import com.thenatekirby.compote.registration.CompoteRegistration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// ====---------------------------------------------------------------------------====

@Mod("compote")
public class Compote {
    public static final String MOD_ID = "compote";

    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("unused")
    static Logger getLogger() {
        return LOGGER;
    }

    public Compote() {
        MinecraftForge.EVENT_BUS.register(this);
        CompoteRegistration.register();
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        VanillaComposterIntegration.registerVanillaComposterIntegration(event.getServer().getRecipeManager());
    }
}
