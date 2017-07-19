package za.co.kmotsepe.tasuku;

/**
 *
 * @author Kingsley Motsepe
 */
public class Issue extends BaseObject{
    String name;
    String description;
    String severity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeverity(String severity){
        this.severity = severity;
    }
    
    public String getSeverity(){
        return severity;
    }
}
