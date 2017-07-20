/**
 * Chekstyle related extensions.
 */
package za.co.kmotsepe.tasuku.checkstyle;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Logger;
import za.co.kmotsepe.tasuku.Issue;

/**
 *
 * @author Kingsley Motsepe
 */
public class CheckStyleIssue extends Issue {
    
    /**
     * Application logger
     */
    private static final Logger LOGGER = Logger.getLogger(CheckStyleIssue.class);
    
    /**
     * Author logged issue will be checkstyle
     */
    //TODO perhaps have this as a label/category
    public static final String AUTHOR = "CheckStyle";

    /**
     * Ticket description
     */
    @Getter
    @Setter
    //TODO maybe move this to method level
    private StringBuilder bufferedDescription;

    /**
     * Checkstyle audit event.
     */
    @Getter
    @Setter
    private AuditEvent auditEvent; //TODO unused variable?

    /**
     * default constructor
     */
    public CheckStyleIssue() {
    }

    /**
     *
     * @param auditEvent
     * @throws ClassNotFoundException
     * @throws MalformedURLException
     * @throws ClassCastException
     * @throws IOException
     */
    public CheckStyleIssue(final AuditEvent auditEvent)
            throws ClassNotFoundException, MalformedURLException,
            ClassCastException, IOException {
        populateIssue(auditEvent);
    }

    /**
     *
     * @param auditEvent Checkstyle audit event
     * @throws ClassNotFoundException
     * @throws MalformedURLException
     * @throws ClassCastException
     * @throws IOException
     */
    final void populateIssue(final AuditEvent auditEvent)
            throws ClassNotFoundException, MalformedURLException,
            ClassCastException, IOException {

        bufferedDescription = new StringBuilder();

        File javaFile = new File(auditEvent.getFileName());

        setName(auditEvent.getMessage());
        setDescription(bufferedDescription.append("Line number: ")
                .append(auditEvent.getLine())
                .append("\nFilename: ")
                .append(FilenameUtils.getName(auditEvent.getFileName()))
                .append("\nPackage: ")
                .append(getPackage(javaFile)).toString());

        LOGGER.info("Issue properties: " + this);
    }

    /**
     *
     * @param javaFile path to java file
     * @return
     */
    private String getPackage(final File javaFile) throws IOException {
        LineIterator it = FileUtils.lineIterator(javaFile, "UTF-8");
        //TODO This is a hack. Need to come up with a better way
        try {
            while (it.hasNext()) {
                String line = it.nextLine();

                if (line.startsWith("package")) {
                    return line;
                }
            }
        } finally {
            LineIterator.closeQuietly(it);
        }

        return null;
    }
}
