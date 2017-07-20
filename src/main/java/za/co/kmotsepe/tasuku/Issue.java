/**
 * Base objects
 */
package za.co.kmotsepe.tasuku;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Kingsley Motsepe
 */
public class Issue extends BaseObject {

    /**
     * issue name
     */
    @Getter
    @Setter
    private String name;

    /**
     * Issue description
     */
    @Getter
    @Setter
    private String description;

    /**
     * issue severity
     */
    @Getter
    @Setter
    String severity;
}
