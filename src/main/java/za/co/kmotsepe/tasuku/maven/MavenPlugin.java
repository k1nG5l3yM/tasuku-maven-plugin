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

    Configuration configuration;
    CheckStyleListener checkstyleListener;
    Checker checker;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        
        checkstyleListener = new CheckStyleListener();
        getLog().info("Tasku Maven plugin started");
        

        //FIX-ME There should be an elegant way of doing this since a lot of plugins will be executed from within here

        File baseFolder = new File("src/");
        System.out.println("base folder: " + baseFolder.getAbsolutePath());

        List files = new ArrayList();
        File testSourceFolder = new File(baseFolder.getAbsoluteFile().toString());
        System.out.println("Checkstyle test folder: " + testSourceFolder.getAbsolutePath());

        listFiles(files, testSourceFolder, "java");
        System.out.println("Found " + files.size() + " Java source files.");

        ByteArrayOutputStream sos = new ByteArrayOutputStream();

        InputSource inputSource = new InputSource("checkstyle.xml");

        try {
            configuration = ConfigurationLoader.loadConfiguration(inputSource, new PropertiesExpander(System.getProperties()), false);
        } catch (CheckstyleException ex) {
            Logger.getLogger(MavenPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            checker = new Checker();
            checker.setModuleClassLoader(Checker.class.getClassLoader());
            checker.configure(configuration);
            checker.addListener(checkstyleListener);
            
            int errors = checker.process(files);
            
            checker.destroy();
            
            System.out.println("Found " + errors + " check style errors.");
            System.out.println(sos.toString());
        } catch (CheckstyleException ex) {
            Logger.getLogger(MavenPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //FIX-ME This should be in a utility class, [or better already exists in another lib]
    private static void listFiles(List files, File folder, String extension) {
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
