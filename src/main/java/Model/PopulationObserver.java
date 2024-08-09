
package Model;

/**
 * The purpose of this class is to observe any changes
 * made to the population model. 
 */

public class PopulationObserver {
    
    public PopulationModel model;
    
    /** Updates the model.
     * @param model The model to be updated
    */ 
    public void update(PopulationModel model){
        this.model = model;
    }
}
