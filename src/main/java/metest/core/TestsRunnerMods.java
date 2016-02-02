package metest.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_ID, version = "1.0", acceptableRemoteVersions = "*")
public class TestsRunnerMods {

    @SuppressWarnings("unused") private static boolean isServerStarted;
    public static MinecraftServer game;
    public static Logger log;
    public static File configFile;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ev) {
        log = ev.getModLog();
        configFile = ev.getSuggestedConfigurationFile();
    }

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
