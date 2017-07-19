/**
 * Base objects
 */
package za.co.kmotsepe.tasuku;

import com.google.gson.Gson;

/**
 *
 * @author Kingsley Motsepe
 */
public abstract class BaseObject {

    /**
     * default constructor
     */
    public BaseObject() {
    }

    //TODO possibly rather have toString() and toJSON().
    /**
     * @return JSON representation of object properties
     */
    @Override
    public String toString() {
        Gson gson = new Gson();

        return gson.toJson(this);
    }
}
