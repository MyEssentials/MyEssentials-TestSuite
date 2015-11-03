package metest.mectest;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import metest.mectest.datasource.DatasourceTest;
import org.apache.logging.log4j.Logger;

/**
 * Class that will test functionality of MEC
 */
@Mod(modid = "MEC-Test", version = "0.00r", acceptableRemoteVersions = "*")
public class MECTest {

    @Mod.Instance
    public static MECTest instance;
    public Logger LOG;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ev) {
        LOG = ev.getModLog();
        Config.instance.init(ev.getSuggestedConfigurationFile(), "MEC-Test");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent ev) {
        DatasourceTest.instance.test();
    }

    @Mod.EventHandler
    public void preInit(FMLPostInitializationEvent ev) {

    }
}
