/*
 * This file is part of Michael Vorburger's SwissKnightMinecraft project, licensed under the MIT License (MIT).
 *
 * Copyright (c) Michael Vorburger <http://www.vorburger.ch>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package metest.api;

import metest.core.MinecraftServerStarter;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.TestClass;

/**
 * JUnit Runner which runs tests inside the ClassLoader of the Minecraft Server.
 *
 * @author Michael Vorburger
 */
public class MinecraftRunner extends BlockJUnit4ClassRunner {

    private static MinecraftServerStarter starter = MinecraftServerStarter.INSTANCE();

    public MinecraftRunner(Class<?> testClass) throws InitializationError {
        super(trick(testClass));
    }

    // This is done like this just so that we can run stuff before invoking the parent constructor
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

    protected static TestClass createTestClass(Class<?> testClass) {
        try {
            ClassLoader classLoader = starter.getMinecraftServerClassLoader();
            Class<?> testClassFromMinecraftClassLoader = classLoader.loadClass(testClass.getName());
            return new TestClass(testClass);
        } catch (ClassNotFoundException e) {
            // This really should never happen..
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Object createTest() throws Exception {
        Object test = super.createTest();
        // TODO Later support @Inject, similar to https://github.com/eclipse/xtext/blob/master/plugins/org.eclipse.xtext.junit4/src/org/eclipse/xtext/junit4/XtextRunner.java
        test.getClass().getField("server").set(test, starter.getGame());
        test.getClass().getField("LOG").set(test, starter.getLog());
        test.getClass().getField("configFile").set(test, starter.getConfigFile());
        return test;
    }

}