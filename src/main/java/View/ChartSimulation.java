package View;

import Model.PopulationModel;
import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/** ChartSimulation is the class that makes the line chart possible. It has 
 * functions that use the epidemic information to build a corresponding 
 * line chart in the results page. 
*/ 

public class ChartSimulation {
    
   
    XYSeries chartS = new XYSeries("S");    // Label lines for chart
    XYSeries chartE = new XYSeries("E");
    XYSeries chartI = new XYSeries("I");
    XYSeries chartR = new XYSeries("R");
    
    /** makeChart takes in the epidemic information time frame and current day 
     * to build a line chart. 
     * @param epidemic where the data is stored to be printed for each day
     * @param i the current day for printing data
     * @return 
    */ 
    public ChartPanel makeChart(PopulationModel epidemic, int i) {
        
        
        
        int xAxis = i;
        chartS.add(xAxis, epidemic.S.get(i)); // Add points to lines
        chartE.add(xAxis, epidemic.E.get(i));
        chartI.add(xAxis, epidemic.I.get(i));
        chartR.add(xAxis, epidemic.R.get(i));
        
        XYSeriesCollection simulation = new XYSeriesCollection(); // Combine lines into collection
        simulation.addSeries(chartS);
        simulation.addSeries(chartE);
        simulation.addSeries(chartI);
        simulation.addSeries(chartR);

        JFreeChart chart = ChartFactory.createXYLineChart(
                
                "Population Simulation During Epidemic",
                "Days",
                "Population",
                simulation,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        
        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.yellow);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesPaint(2, Color.green);
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        renderer.setSeriesPaint(3, Color.blue);
        renderer.setSeriesStroke(3, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        chart.setBackgroundPaint(Color.DARK_GRAY);
        chart.getTitle().setPaint(new java.awt.Color(242,242,242));
        
        plot.setBackgroundPaint(Color.DARK_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);
        plot.getRangeAxis().setLabelPaint(Color.lightGray);
        plot.getDomainAxis().setLabelPaint(Color.LIGHT_GRAY);
        plot.getRangeAxis().setTickLabelPaint(Color.LIGHT_GRAY);
        plot.getDomainAxis().setTickLabelPaint(Color.LIGHT_GRAY);
        
        ChartPanel panel = new ChartPanel(chart);  
        
        return panel;
}  
}

