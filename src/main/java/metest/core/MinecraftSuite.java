package metest.core;

import metest.api.BaseSuite;
import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import java.util.List;

public class MinecraftSuite extends Suite {
    private static MinecraftServerStarter starter = MinecraftServerStarter.INSTANCE();

    public MinecraftSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        super(klass, builder);
    }

    protected MinecraftSuite(Class<?> klass, List<Runner> runners) throws InitializationError {
        super(klass.isAssignableFrom(BaseSuite.class) ? trick(klass) : klass, runners);
    }


    private static Class<?> trick(Class<?> testClass) throws InitializationError {
        try {
            if (!starter.isRunning()) {
                starter.startServer();
                starter.waitForServerStartupCompletion();
            }
        } catch (Throwable e) {
            throw new InitializationError(e);
        }
        try {
            ClassLoader classLoader = starter.getMinecraftServerClassLoader();
            return classLoader.loadClass(testClass.getName());
        } catch (ClassNotFoundException e) {
            // This really should never happen..
            throw new RuntimeException(e);
        }
    }
}
