package Model;

import static java.lang.Math.floor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * PopulationModel is the model for the program.
 * PopulationModel creates a SEIR model that simulates a population of people.
 * It accomplishes this with lists, including one for that tracks the # of deaths.
 * The variables of PopulationModel are user defined in order to emphasize interactivity.
 * Population, timeframe, and infected are all decided directly by the user using a slider.
 * timeframe controls the number of loops for simulate and is used to get the ending values
 * of SEIR lists. 
 * 
 * infectionRate, recoveryStart/End, and incubationStart/End are determined by a preset data 
 * group based on the user's disease selection in the GUI.
 * Disease list:
 * 1 for covid, 2 for flu, 3 for bubonic,
 * 4 for 1918 flu, 5 for delta and 6 for smallpox
 */
public class PopulationModel {
    private int population; // Population defined by user
    private int timeframe;  // In days, controls # of loops, defined by user
    private int infected;   // Initial # of infected people, defined by user
    private double infectionRate;   // Infection rate, preselected variable by disease
    private double recoveryStart;   // Format: 1 / Random number between average start and end, preselected variable by disease
    private double recoveryEnd;
    private double incubationStart; // Format: 1 / days-until-sickness, preselected variable by disease
    private double incubationEnd;
    public List<Double> S = new ArrayList<>();  // Susceptible
    public List<Double> E = new ArrayList<>();  // Exposed
    public List<Double> I = new ArrayList<>();  // Infected
    public List<Double> R = new ArrayList<>();  // Recovered
    public List<Integer> D = new ArrayList<>(); // Dead

    double vacEff;
    int halfDead = 0;
    int disease;
    double vaccinatedPop;
    double quarantinePop;
    
    /** setPop sets the private variable population.
     * @param pop // Population defined by user
    */
    public void setPop(int pop){
        population = pop;
    }
    
    /** setTimeFrame sets the private variable timeframe.
     * @param time In days, controls # of loops, defined by user
    */
    public void setTimeframe(int time){
        timeframe = time;
    }
    
    /** getTimeFrame returns the private variable timeframe.
     * @return simple get function for timeframe
    */
    public int getTimeframe(){
        return timeframe;
    }
    
    /** setInfected sets the private variable infected.
     * @param inf Initial # of infected people, defined by user
    */
    public void setInfected(int inf){
        infected = inf;
    }
    
    /** setDisease sets the private variable disease.
     * @param sickness Sickness is a number related to a disease, requires preprocessed data
     * @param vaccine Total percent of population that is vaccinated
     * @param quarantine Total percent of population that quarantines when sick
    */
    public void setDisease(int sickness, double vaccine, double quarantine){
        disease = sickness;
        vaccinatedPop = vaccine;
        quarantinePop = quarantine;
        
        setDiseaseData();
    }
    
    /** getDisease gets the disease variable as a string
     * @return Returns the name of the disease chosen as a string
    */
    public String getDisease(){
        String diseaseName;
        
        switch (disease) {
            case 1 -> diseaseName = "Covid-19";
            case 2 -> diseaseName = "Seasonal Flu";
            case 3 -> diseaseName = "Bubonic Plague";
            case 4 -> diseaseName = "1918 Flu";
            case 5 -> diseaseName = "Delta Variant";
            case 6 -> diseaseName = "Smallpox";
            default -> { diseaseName = "Error";
            }
        }
        
        return diseaseName;
    }
    
    /** 
     * setDiseaseData creates the data about the diseases necessary to simulate a
     * SEIR model. Assumes preprocessed data to correlate diseases to integers. 
     * Uses a switch to adjust for the disease sent to it. Variables it fulfills are:
     * recoveryStart/End, incubationStart/End, vaccine/quarantine effectiveness, 
     * and infection rate
     */
    private void setDiseaseData(){
        
        final double covidBase = 0.26;
        final double fluBase = 0.19;
        final double bubonicBase = 0.45;
        final double spanishFluBase = 0.22;
        final double deltaBase = .506;
        final double smallpoxBase = 0.25;
            
        switch (disease) {
            case 1 -> {
                recoveryStart = 7.0;
                recoveryEnd = 14.0;
                incubationStart = 5.0;
                incubationEnd = 7.0;
                
                // 1 - (% of population * effectiveness of safeguard / population)
                vacEff = 1 - (((population * (vaccinatedPop / 100.0)) * 0.6) / population);
                double quarEff = 1 - (((population * (quarantinePop / 100.0)) * 0.4) / population);
                
                infectionRate = (covidBase * vacEff) * quarEff;
            }
            case 2 -> {
                recoveryStart = 7.0;
                recoveryEnd = 14.0;
                incubationStart = 1.0;
                incubationEnd = 4.0;
                
                vacEff = 1 - (((population * (vaccinatedPop / 100.0)) * 0.3) / population);
                double quarEff = 1 - (((population * (quarantinePop / 100.0)) * 0.8) / population);

                infectionRate = (fluBase * vacEff) * quarEff;
            }
            case 3 -> {
                recoveryStart = 3.0;
                recoveryEnd = 6.0;
                incubationStart = 2.0;
                incubationEnd = 8.0;
                
                infectionRate = bubonicBase;
            }
            case 4 -> {
                recoveryStart = 7.0;
                recoveryEnd = 11.0;
                incubationStart = 1.0;
                incubationEnd = 4.0;
                
                infectionRate = spanishFluBase;
            }
            case 5 -> {
                recoveryStart = 7.0;
                recoveryEnd = 14.0;
                incubationStart = 3.0;
                incubationEnd = 5.0;
                
                vacEff = 1 - (((population * (vaccinatedPop / 100.0)) * 0.7) / population);
                double quarEff = 1 - (((population * (quarantinePop / 100.0)) * 0.4) / population);
                
                infectionRate = (deltaBase * vacEff) * quarEff;
            }
            case 6 -> {
                recoveryStart = 14.0;
                recoveryEnd = 28.0;
                incubationStart = 7.0;
                incubationEnd = 17.0;
                
                vacEff = 1 - (((population * (vaccinatedPop / 100.0)) * 0.9) / population);
                
                infectionRate = smallpoxBase * vacEff;
            }
        }
            
    }

    /**
     * getSimulatedPop creates the model data and adds it to the lists S, E, I, and R based 
     * on the data collected from the user.
     * simulate initializes the first set of data based entirely on user input
     * and then calculates the continuing days based on the timeframe variable.
     * 
     * timeframe, infectionRate, population, recoveryStart/End, incubationStart/End
     * all must be collected before simulate can be ran.
     * 
     * The calculations are based on formula for the SEIR model.
     * 
     * @param mutation Whether or not the virus will undergo mutation mid simulation
     */
    public void getSimulatedPop(boolean mutation) {
        S.add((double) population - infected);    // Initial PopulationModel values
        E.add((double) infected);
        I.add((double) infected);
        R.add(0.0);
        D.add(0);
        int mutateTime = timeframe / 2;
        int newD = 0;

        for (int i = 0; i < timeframe; i++) {
            double newlyExposed = (infectionRate * S.get(i) * I.get(i)) / population;
            double recovery = 1 / ThreadLocalRandom.current().nextDouble(recoveryStart, recoveryEnd);
            double incubation = 1 / ThreadLocalRandom.current().nextDouble(incubationStart, incubationEnd);

            double newS = S.get(i) - (newlyExposed);
            double newE = E.get(i) + (newlyExposed - (incubation * E.get(i)));
            double newI = I.get(i) + ((incubation * E.get(i)) - (recovery * I.get(i)));
            double newR = R.get(i) + (recovery * I.get(i));
            
            switch (disease) {
                case 1 -> {
                    newD = ((int) floor(R.get(i) * .0007)) + halfDead;
                }
                case 2 -> {
                    newD = ((int) floor(R.get(i) * .0002)) + halfDead;
                }
                case 3 -> {
                    newD = ((int) floor(R.get(i) * .45)) + halfDead;
                }
                case 4 -> {
                    newD = ((int) floor(R.get(i) * .025)) + halfDead;
                }
                case 5 -> {
                    newD = ((int) floor(R.get(i) * .03)) + halfDead;
                }
                case 6 -> {
                    newD = ((int) floor(R.get(i) * .3)) + halfDead;
                }
            }
            
            if(mutation == true && i == mutateTime){
                newS = newS + (newR - newD);
                newR = newD;
                halfDead = newD;
                
                if(disease != 3 && disease != 4) {
                    infectionRate = infectionRate / vacEff;
                }
            }
            
            S.add(newS);
            E.add(newE);
            I.add(newI);
            R.add(newR);
            D.add(newD);
        }
    }
}
