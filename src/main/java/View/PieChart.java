
package View;

import Model.PopulationModel;
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.labels.PieSectionLabelGenerator;  
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;  
import org.jfree.chart.plot.PiePlot;  
import org.jfree.data.general.DefaultPieDataset;  
import org.jfree.data.general.PieDataset;
import java.text.DecimalFormat;  
import java.awt.Color;


/**
 * PieChart makes the pie charts that are shown on the results page. This class
 * makes the animations possible as well. 
 * 
 */

public class PieChart {
    public ChartPanel chartPanel;  
    public ChartPanel chartPanelMortality;
    
    /**
     * PieChart takes information from the updated model and 
     * creates a pie chart based on that, with each 'start'.
     * @param epidemic Simulated data from model
     * @param timeframe Total days for simulation
     */    
    public PieChart(PopulationModel epidemic, int timeframe) {  
   
        PieDataset dataset = createDataset(epidemic, timeframe);  
        JFreeChart chart = ChartFactory.createPieChart(  
            "SEIR",  
            dataset,  
            true,   
            true,  
            false);  

        PieDataset datasetMortality = createDatasetMortality(epidemic, timeframe);  
        JFreeChart chartMortality = ChartFactory.createPieChart(  
            "Mortality Rate",  
            datasetMortality,  
            true,   
            true,  
            false); 
        
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setSectionPaint("Susceptible", Color.RED);
        piePlot.setSectionPaint("Exposed", Color.YELLOW);
        piePlot.setSectionPaint("Infected", Color.GREEN);
        piePlot.setSectionPaint("Recovered", Color.BLUE);
        
        PiePlot piePlotMortality = (PiePlot) chartMortality.getPlot();
        piePlotMortality.setSectionPaint("Never Sick", Color.WHITE);
        piePlotMortality.setSectionPaint("Died", new Color(138,3,3));
        piePlotMortality.setSectionPaint("Lived", Color.GRAY);


        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(  
            "{0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));  
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);  
        chart.setBackgroundPaint(Color.DARK_GRAY);
        chart.getTitle().setPaint(new java.awt.Color(242,242,242));
        chart.getPlot().setBackgroundPaint(Color.DARK_GRAY);
        chart.setBorderVisible(false);
        chart.getPlot().setOutlineVisible(false);
        chartPanel = new ChartPanel(chart);  
        
        PieSectionLabelGenerator labelGeneratorMortality = new StandardPieSectionLabelGenerator(  
            "{0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));  
        ((PiePlot) chartMortality.getPlot()).setLabelGenerator(labelGeneratorMortality);  
        chartMortality.setBackgroundPaint(Color.DARK_GRAY);
        chartMortality.getTitle().setPaint(new java.awt.Color(242,242,242));
        chartMortality.getPlot().setBackgroundPaint(Color.DARK_GRAY);
        chartMortality.setBorderVisible(false);
        chartMortality.getPlot().setOutlineVisible(false);
        chartPanelMortality = new ChartPanel(chartMortality); 
    }  
    
    /**
     * How the pie chart determines how it's going to fill out. (What each slice is)
     * @param epidemic data to be read from
     * @param timeframe index tool
     */    
    private PieDataset createDataset(PopulationModel epidemic, int timeframe) {  
   
    DefaultPieDataset dataset=new DefaultPieDataset();  
    dataset.setValue("Susceptible", epidemic.S.get(timeframe));  
    dataset.setValue("Exposed", epidemic.E.get(timeframe));  
    dataset.setValue("Infected", epidemic.I.get(timeframe));  
    dataset.setValue("Recovered", epidemic.R.get(timeframe));  
    return dataset;  
    }  
    
    /**
     * How the mortality pie chart determines how it's going to fill out. 
     * (What each slice is)
     * @param epidemic data to be read from
     * @param timeframe index tool
     */
    private PieDataset createDatasetMortality(PopulationModel epidemic, int timeframe) {  
   
    DefaultPieDataset dataset=new DefaultPieDataset();  
    dataset.setValue("Never Sick", epidemic.S.get(timeframe));
    dataset.setValue("Died", epidemic.D.get(timeframe));
    dataset.setValue("Lived", epidemic.R.get(timeframe) - epidemic.D.get(timeframe));  
    return dataset;  
    }  
    
    /**
     * updatePieChart renders the next frame of the animation.
     * @param epidemic The data to be charted
     * @param i The index for days for the data, used for animating
     */  
    public void updatePieChart(PopulationModel epidemic, int i) {
        PieDataset dataset = createDataset(epidemic, i);
        JFreeChart chart = chartPanel.getChart();
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setDataset(dataset);
        
        PieDataset datasetMortality = createDatasetMortality(epidemic, i);
        JFreeChart chartMortality = chartPanelMortality.getChart();
        PiePlot plotMortality = (PiePlot) chartMortality.getPlot();
        plotMortality.setDataset(datasetMortality);
        
        chartPanel.repaint();
        chartPanelMortality.repaint();
    }
}