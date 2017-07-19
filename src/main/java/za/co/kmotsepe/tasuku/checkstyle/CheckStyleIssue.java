package za.co.kmotsepe.tasuku.checkstyle;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import za.co.kmotsepe.tasuku.Issue;

/**
 *
 * @author Kingsley Motsepe
 */
public class CheckStyleIssue extends Issue {

    public static final String AUTHOR = "CheckStyle";
    StringBuilder bufferedDescription;
    AuditEvent auditEvent;

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
    public CheckStyleIssue(AuditEvent auditEvent) throws ClassNotFoundException, 
            MalformedURLException, ClassCastException, IOException {
        populateIssue(auditEvent);
    }
    
    /**
     * 
     * @param auditEvent
     * @throws ClassNotFoundException
     * @throws MalformedURLException
     * @throws ClassCastException
     * @throws IOException 
     */
    final void populateIssue(AuditEvent auditEvent)
            throws ClassNotFoundException, MalformedURLException, ClassCastException, IOException {

        bufferedDescription = new StringBuilder();
        
        File javaFile = new File(auditEvent.getFileName());

        setName(auditEvent.getMessage());
        setDescription(bufferedDescription.append("Line number: ").append(auditEvent.getLine())
                .append("\nFilename: ").append(FilenameUtils.getName(auditEvent.getFileName()))
                .append("\nPackage: ")
                .append(getPackage(javaFile)).toString());

        /*ClassLoaderUtil.getClass(urlClassLoader, 
                        FilenameUtils.getBaseName(auditEvent.getFileName())).getPackage()).toString()*/
        System.out.println("Issue properties: " + this);
    }

    /**
     *
     * @param fileName path to java file
     * @return
     */
    private String getPackage(File javaFile) throws IOException {
        LineIterator it = FileUtils.lineIterator(javaFile, "UTF-8");
        
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                
                if(line.startsWith("package")){
                    return line;
                }
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
        
        return null;
    }
}
