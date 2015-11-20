package metest;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import net.minecraft.server.MinecraftServer;

@Mod(modid = "Integration Test Runner", name = "Integration Test Runner", version = "1.0", acceptableRemoteVersions = "*")
public class TestsRunnerMods {

    @SuppressWarnings("unused") private static boolean isServerStarted;
    public static MinecraftServer game;

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent ev) {
        isServerStarted = true;
        System.out.println("Server has been instantiated");
        game = ev.getServer();
    }

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent ev) {
        isServerStarted = false;
    }

}
