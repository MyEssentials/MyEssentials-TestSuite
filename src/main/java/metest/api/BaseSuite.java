package metest.api;

import metest.core.MinecraftServerStarter;
import org.junit.AfterClass;

public class BaseSuite {
    @AfterClass
    public static void tearDown() {
        try {
            MinecraftServerStarter.INSTANCE().getGame().getClass().getMethod("stopServer").invoke(MinecraftServerStarter.INSTANCE().getGame());
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
