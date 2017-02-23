package algoritmogenetico;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

class Grafica extends ApplicationFrame{
    
    ArrayList <Double> grafica = new ArrayList <>();
    ArrayList <Double> mejorFitness, coche;
    
    public Grafica(ArrayList grafica) {
        super("MejorFitness");
        this.grafica = grafica;
        setContentPane(createPanel());
        pack();
    }

    public Grafica(ArrayList<Double> mejorFitness, ArrayList<Double> mejorFitnessCoche, ArrayList<Double> conjunto) {
        super("Pareto");
        this.mejorFitness = mejorFitness;
        this.coche = mejorFitnessCoche;
        
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries data = new XYSeries("");
        
        Double media = 0.00;
        for (Double items : conjunto) {
            media += items;
        }
        media /= conjunto.size();
        
        for (int i = 0; i < mejorFitness.size(); i++) {
            if (conjunto.get(i) > media)
            data.add(mejorFitness.get(i), mejorFitnessCoche.get(i));
        }
        data.add(0,0);
        data.add(1,0);
        result.addSeries(data);
        
        JFreeChart chart = ChartFactory.createScatterPlot("PARETO", "Fitness circulación", "Fitness coches", result, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500,450));
        setContentPane(chartPanel);
        
        pack();
    }
    
    

    private ChartPanel createPanel() {
        ChartPanel chartPanel = new ChartPanel(createChart(createDataset()));
        chartPanel.setPreferredSize(new Dimension(500,450));
        return chartPanel;
    }
    
    private JFreeChart createChart (DefaultCategoryDataset dataset){
        JFreeChart chart;
        if(grafica.size() != 0){
            chart = ChartFactory.createLineChart("Mejores fitness", "Fitness", "Número de la generación",
                dataset, PlotOrientation.VERTICAL, false, false, false);
        }else{
            chart = ChartFactory.createScatterPlot("PARETO", "Fitness circulación", "Fitness coches", (XYDataset) dataset, PlotOrientation.VERTICAL, false, false, false);
        }
        return chart;
    }
    
    private DefaultCategoryDataset createDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if(grafica.size() != 0){
            for (int i = 0; i < grafica.size(); i++) {
                dataset.addValue(grafica.get(i), "", Integer.toString(i));
            }
        }else {
            for (int i = 0; i < mejorFitness.size(); i++) {
                dataset.addValue(mejorFitness.get(i), "", coche.get(i));
            }
        }
        return dataset;
    }
    
    public void execute(){
        setVisible(true);
    }

}
