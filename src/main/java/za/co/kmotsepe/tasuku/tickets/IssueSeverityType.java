/**
 * Generic ticket classes plus specific ticket builders
 */
package za.co.kmotsepe.tasuku.tickets;

import za.co.kmotsepe.tasuku.BaseObject;

/**
 * <p>
 * Class representing the issue level for Tickets</p>
 *
 * @author Kingsley Motsepe
 */
public class IssueSeverityType extends BaseObject implements Type {

    /**
     * String representation of severity type - trivial
     */
    public static final String TRIVIAL = "TRIVIAL";

    /**
     * String representation of severity type - low
     */
    public static final String LOW = "LOW";

    /**
     * String representation of severity type - medium
     */
    public static final String MEDIUM = "MEDIUM";

    /**
     * String representation of a severity type - high
     */
    public static final String HIGH = "HIGH";
}
