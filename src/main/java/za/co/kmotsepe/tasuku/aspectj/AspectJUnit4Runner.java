/**
 * AspectJ classes - Currently for checking the CheckStyleListener AuditEvent
 */
package za.co.kmotsepe.tasuku.aspectj;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.aspectj.weaver.loadtime.WeavingURLClassLoader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.statements.RunAfters;
import org.junit.internal.runners.statements.RunBefores;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

/**
 * <p>
 * Use this JUnit Runner if you want to enable AspectJ load time weaving in your
 * test. To use this runner place this annotation on your test class:</p>
 * <p>
 * {@code @RunWith(AspectJUnit4Runner.class)}</p>
 *
 * @see AspectJConfig
 * @author David Zhang
 */
public class AspectJUnit4Runner extends BlockJUnit4ClassRunner {

    /**
     *
     */
    private WeavingURLClassLoader cl;

    /**
     *
     */
    private TestClass testClass;

    /**
     *
     * @param clazz
     * @throws InitializationError
     */
    public AspectJUnit4Runner(final Class<?> clazz) throws InitializationError {
        super(clazz);
        System.out.println("AspectJunit4Runner class loaded");
    }

    /**
     *
     * @param clazz
     * @return
     */
    @Override
    protected final TestClass createTestClass(Class<?> clazz) {
        URL[] classpath = computeClasspath(clazz);
        cl = new WeavingURLClassLoader(classpath, null);
        clazz = loadClassFromClassLoader(clazz, cl);
        testClass = new TestClass(clazz);
        return testClass;
    }

    /**
     *
     * @param clazz
     * @return
     */
    private URL[] computeClasspath(final Class<?> clazz) {
        URLClassLoader originalClassLoader
                = (URLClassLoader) clazz.getClassLoader();
        URL[] classpath = originalClassLoader.getURLs();
        AspectJConfig config = clazz.getAnnotation(AspectJConfig.class);
        if (config != null) {
            classpath = appendToClasspath(classpath,
                    config.classpathAdditions());
        }
        return classpath;
    }

    /**
     *
     * @param classpath
     * @param urls
     * @return
     */
    private URL[] appendToClasspath(final URL[] classpath,
            final String[] urls) {
        URL[] extended = Arrays.copyOf(classpath,
                classpath.length + urls.length);
        for (int i = 0; i < urls.length; i++) {
            URL url;
            try {
                url = Paths.get(urls[i]).toAbsolutePath().toUri().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            extended[classpath.length + i] = url;
        }
        return extended;
    }

    /**
     *
     * @return
     */
    @Override
    protected final List<FrameworkMethod> computeTestMethods() {
        Class<? extends Annotation> test
                = loadClassFromClassLoader(Test.class, cl);
        return getTestClass().getAnnotatedMethods(test);
    }

    /**
     *
     * @param notifier
     */
    @Override
    public final void run(final RunNotifier notifier) {
        Throwable firstException = null;
        try {
            super.run(notifier);
        } catch (Exception e) {
            firstException = e;
            throw e;
        } finally {
            try {
                cl.close();
            } catch (IOException e) {
                String exceptionMsg = "Failed to close AspectJ classloader.";
                RuntimeException rte
                        = new RuntimeException(exceptionMsg, e);
                if (firstException != null) {
                    rte.addSuppressed(firstException);
                }
                throw rte;
            }
        }
    }

    /**
     *
     * @param statement
     * @return
     */
    @Override
    protected final Statement withBeforeClasses(final Statement statement) {
        Class<? extends Annotation> beforeClass
                = loadClassFromClassLoader(BeforeClass.class, cl);
        List<FrameworkMethod> befores
                = testClass.getAnnotatedMethods(beforeClass);

        if (befores.isEmpty()) {
            return statement;
        } else {
            return new RunBefores(statement, befores, null);
        }
    }

    /**
     *
     * @param statement
     * @return
     */
    @Override
    protected final Statement withAfterClasses(final Statement statement) {
        Class<? extends Annotation> afterClass
                = loadClassFromClassLoader(AfterClass.class, cl);
        List<FrameworkMethod> afters
                = testClass.getAnnotatedMethods(afterClass);

        if (afters.isEmpty()) {
            return statement;
        } else {
            return new RunAfters(statement, afters, null);
        }
    }

    /**
     *
     * @param method
     * @param target
     * @param statement
     * @return
     */
    @Override
    protected final Statement withBefores(final FrameworkMethod method,
            final Object target, final Statement statement) {
        Class<? extends Annotation> before
                = loadClassFromClassLoader(Before.class, cl);
        List<FrameworkMethod> befores
                = getTestClass().getAnnotatedMethods(before);

        if (befores.isEmpty()) {
            return statement;
        } else {
            return new RunBefores(statement, befores, target);
        }
    }

    /**
     *
     * @param method
     * @param target
     * @param statement
     * @return
     */
    @Override
    protected final Statement withAfters(final FrameworkMethod method,
            final Object target, final Statement statement) {
        Class<? extends Annotation> after
                = loadClassFromClassLoader(After.class, cl);
        List<FrameworkMethod> afters
                = getTestClass().getAnnotatedMethods(after);

        if (afters.isEmpty()) {
            return statement;
        } else {
            return new RunAfters(statement, afters, target);
        }
    }

    /**
     *
     * @param <T>
     * @param clazz
     * @param cl
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <T> Class<T> loadClassFromClassLoader(final Class<T> clazz,
            final ClassLoader cl) {
        Class<T> loaded;
        try {
            loaded = (Class<T>) Class.forName(clazz.getName(), true, cl);
            System.out.println("Class " + loaded.getCanonicalName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loaded;
    }
}
