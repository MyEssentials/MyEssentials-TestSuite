package metest.api;

import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(MinecraftRunner.class)
public class BaseTest {

    public MinecraftServer server;
    public Logger LOG;
    public File configFile;

}
