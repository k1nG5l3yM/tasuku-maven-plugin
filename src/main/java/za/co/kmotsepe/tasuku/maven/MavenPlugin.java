/**
 * Maven plugin(s)
 */
package za.co.kmotsepe.tasuku.maven;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.xml.sax.InputSource;
import za.co.kmotsepe.tasuku.checkstyle.CheckStyleListener;

/**
 *
 * @author Kingsley Motsepe
 */
@Mojo(name = "codecheck")
public class MavenPlugin extends AbstractMojo {

    /**
     * Application logger
     */
    private static final org.apache.log4j.Logger LOGGER
            = org.apache.log4j.Logger.getLogger(MavenPlugin.class);

    /**
     *
     * @throws MojoExecutionException
     * @throws MojoFailureException
     */
    @Override
    public final void execute() throws MojoExecutionException,
            MojoFailureException {
        Checker checker;
        CheckStyleListener checkstyleListener;
        Configuration configuration;

        checkstyleListener = new CheckStyleListener();
        getLog().info("Tasku Maven plugin started");

        //FIX-ME There should be an elegant way of doing this since a lot of 
        //plugins will be executed from within here
        File baseFolder = new File("src/");
        LOGGER.info("base folder: " + baseFolder.getAbsolutePath());

        List files = new ArrayList();
        File testSourceFolder = new File(baseFolder.getAbsoluteFile()
                .toString());
        LOGGER.info("Checkstyle test folder: "
                + testSourceFolder.getAbsolutePath());

        listFiles(files, testSourceFolder, "java");
        LOGGER.info("Found " + files.size() + " Java source files.");

        ByteArrayOutputStream sos = new ByteArrayOutputStream();

        InputSource inputSource = new InputSource("checkstyle.xml");

        try {
            configuration = ConfigurationLoader.loadConfiguration(inputSource,
                    new PropertiesExpander(System.getProperties()), false);
            checker = new Checker();
            checker.setModuleClassLoader(Checker.class.getClassLoader());
            checker.configure(configuration);
            checker.addListener(checkstyleListener);

            int errors = checker.process(files);

            checker.destroy();

            LOGGER.debug("Found " + errors + " check style errors.");
            LOGGER.debug(sos.toString());
        } catch (CheckstyleException ex) {
            Logger.getLogger(MavenPlugin.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param files
     * @param folder
     * @param extension
     */
    //FIX-ME This should be in a utility class, 
    //[or better already exists in another lib]
    private static void listFiles(final List files, final File folder,
            final String extension) {
        if (folder.canRead()) {
            if (folder.isDirectory()) {
                for (File f : folder.listFiles()) {
                    listFiles(files, f, extension);
                }
            } else if (folder.toString().endsWith("." + extension)) {
                files.add(folder);
            }
        }
    }
}
