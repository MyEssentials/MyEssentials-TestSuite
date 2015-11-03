package metest.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class Test {

    @Retention(RetentionPolicy.RUNTIME)
    @interface Method {

        /**
         * What is the method supposed to be doing
         */
        String name();

        /**
         * How is it doing it
         */
        String description() default "";

        /**
         * Priority of the test method
         */
        Priority priority() default Priority.NORMAL;

    }

    public enum Priority {
        HIGHEST,
        HIGHER,
        HIGH,
        NORMAL,
        LOW,
        LOWER,
        LOWEST
    }
}
