/**
 * Various code analyser listeners
 */
package za.co.kmotsepe.tasuku.events;

/**
 *
 * @author Kingsley Motsepe
 */
public interface Event {

    /**
     *
     * @param message
     */
    void addMessage(Object message);

    /**
     *
     * @return
     */
    Object getMessage();
}
