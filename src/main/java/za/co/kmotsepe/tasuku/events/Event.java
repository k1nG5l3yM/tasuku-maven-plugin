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
    public void addMessage(Object message);
    
    /**
     * 
     * @return 
     */
    public Object getMessage();
}
