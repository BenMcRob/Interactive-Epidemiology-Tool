package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to house the information from the 
 * population model and it then updates the observer. 
 * (The changing of the population model is what is being "observed").  
 */
public class PopulationObservable {
    private PopulationModel model = new PopulationModel();
    private List<PopulationObserver> observers = new ArrayList<>();
    
    /** addObserver adds an observer that is observing changes made to
     *  the model.
     * @param observer  Observer to be added
    */   
    public void addObserver(PopulationObserver observer) {
        this.observers.add(observer);
    }
    /** removeObserver removes an observer from the list. (We are only using
     * one in this program so this is not called but it's required for implementing
     * an observer).
     * @param observer Observer to be removed
    */
    public void removeObserver(PopulationObserver observer) {
        this.observers.remove(observer);
    }
    /** setModel sets the model that is being observed and notifies all 
     * observers.
     * @param model Population is the only true model in the program
    */ 
    public void setModel(PopulationModel model) {
        this.model = model;
        for (PopulationObserver observer : this.observers) {
            observer.update(this.model);
        }
    }

    
}
