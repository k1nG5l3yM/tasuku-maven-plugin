/**
 * AspectJ classes - Currently for checking the CheckStyleListener AuditEvent
 */
package za.co.kmotsepe.tasuku.aspectj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Courtesy of: https://github.com/david-888/aspectj-junit-runner Under GNU
 * General Public License v3.0
 *
 * @author Kingsley Motsepe
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface AspectJConfig {

    /**
     * Use this to append additional classpath entries. This is especially
     * useful if you want your tests to use different aop.xml files each. If
     * your aop.xml file is located for example in
     * {@code /home/joe/test/META-INF/aop.xml} then add {@code /home/joe/test}
     * as classpathAddition. Depending on your project setup this might also
     * work with relative parts.
     * @return 
     */
    String[] classpathAdditions() default "";
}
