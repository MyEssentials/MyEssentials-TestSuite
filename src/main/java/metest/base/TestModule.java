package metest.base;

import metest.base.exception.TestException;
import metest.mectest.MECTest;

import java.lang.reflect.Method;

/**
 * An object that tests a functionality.
 * It can have a hierarchy of TestModules
 */
public abstract class TestModule {

    public TestModule() {
        for (Method m : getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.Method.class)) {
                Test.Method annot = m.getDeclaredAnnotation(Test.Method.class);
                try {
                    m.invoke(this);
                    MECTest.instance.LOG.info("[METHOD]");
                } catch (Exception ex) {

                }
            }
        }
    }

    protected void ASSERT(boolean statement) {
        if(!statement) {
            throw new AssertionError();
        }
    }

    public abstract void test() throws TestException;
}
