/**
 * Unit tests
 */
package za.co.kmotsepe.tasuku.test;

import java.io.File;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import za.co.kmotsepe.tasuku.aspectj.AspectJConfig;
import za.co.kmotsepe.tasuku.aspectj.AspectJUnit4Runner;
import za.co.kmotsepe.tasuku.maven.MavenPlugin;

/**
 *
 * @author Kingsley Motsepe
 */
//FIX-ME This does not load with old version of surefire maven plugin. 
//New version breaks code too.
@AspectJConfig(classpathAdditions = "src/main/java")
@RunWith(AspectJUnit4Runner.class)
public class CheckStyleListenerTest extends AbstractMojoTestCase {

    /**
     *
     * @throws Exception
     */
    @Before
    @Override
    public final void setUp() throws Exception {
        super.setUp();
    }

    /**
     *
     */
    @After
    @Override
    public final void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of addError method, of class CheckStyleListener.
     *
     * @throws java.lang.Exception
     */
    @Test
    public final void testAddError() throws Exception {

        File pom = new File("src/test/resources/pom.xml");
        assertNotNull("POM file should not be null", pom);

        System.out.println("POM location: " + pom.getAbsolutePath());

        assertTrue("POM file should exist", pom.exists());

        MavenPlugin mavenPlugin = (MavenPlugin) lookupMojo("codecheck", pom);
        assertNotNull("Maven plugin should be loadable from the POM file",
                mavenPlugin);

        mavenPlugin.execute();
    }
}
