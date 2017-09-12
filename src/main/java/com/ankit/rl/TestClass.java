package com.ankit.rl;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class TestClass extends ApplicationFrame
{
    public TestClass( String applicationTitle , String chartTitle ) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "Years","Number of Schools",
                createXYDataSet(),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    private DefaultCategoryDataset createDataset( ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        dataset.addValue( 15 , "schools" , "1970" );
        dataset.addValue( 30 , "schools" , "1980" );
        dataset.addValue( 60 , "schools" ,  "1990" );
        dataset.addValue( 120 , "schools" , "2000" );
        dataset.addValue( 240 , "schools" , "2010" );
        dataset.addValue( 300 , "schools" , "2014" );
        dataset.addValue( 300 , "schools_copy" , "2014" );
        dataset.addValue( 1500 , "schools_copy" , "1990" );
        dataset.addValue( 10 , "schools_copy" , "1970" );
        return dataset;
    }

    private XYSeriesCollection createXYDataSet()
    {
        XYSeries series1 = new XYSeries("series1");
        series1.add(2,3);
        series1.add(4,5);
        XYSeries series2 = new XYSeries("series2");
        series2.add(1,4);
        series2.add(3,6);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }

    public static void main( String[ ] args ) {
        TestClass chart = new TestClass(
                "School Vs Years" ,
                "Numer of Schools vs years");

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }

    public static void printChart()
    {
        TestClass chart = new TestClass(
                "School Vs Years" ,
                "Numer of Schools vs years");

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}

