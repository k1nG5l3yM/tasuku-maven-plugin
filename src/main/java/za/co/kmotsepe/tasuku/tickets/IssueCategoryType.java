/**
 * Generic ticket classes plus specific ticket builders
 */
package za.co.kmotsepe.tasuku.tickets;

import za.co.kmotsepe.tasuku.BaseObject;

/**
 *
 * @author Kingsley Motsepe
 */
public class IssueCategoryType extends BaseObject implements Type {

    /**
     * String representation of a ticket category - bug
     */
    public static final String BUG = "bug";

    /**
     * String representation of a ticket category - feature
     */
    public static final String FEATURE = "feature";

    /**
     * String representation of a ticket category - task
     */
    public static final String TASK = "task";
}
