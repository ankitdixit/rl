package com.ankit.rl.narmbandit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LineChartPlotter extends ApplicationFrame
{
    public LineChartPlotter(String applicationTitle , String chartTitle, List<Double> rewards, List<Double> percentages ) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "Years","Number of Schools",
                createXYDataSet(rewards, percentages),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }

    public LineChartPlotter(String applicationTitle , String chartTitle, List<PolicyPlotterInput> inputs) {
        super(applicationTitle);
        JPanel jPanel = new JPanel();
        List<XYSeriesCollection> xySeriesCollections = createXYDataSet(inputs);
        for ( XYSeriesCollection xySeriesCollection : xySeriesCollections) {
            JFreeChart lineChart = ChartFactory.createXYLineChart(
                    chartTitle,
                    "X axis",
                    "Y axis",
                    xySeriesCollection,
                    PlotOrientation.VERTICAL,
                    true, true, false);
            jPanel.add(new ChartPanel(lineChart));
        }
        setContentPane( jPanel );
    }

    private XYSeriesCollection createXYDataSet(List<Double> rewards, List<Double> percentages)
    {
        XYSeries averageRewardSeries = new XYSeries("averageRewards");
        for(int i=0; i < rewards.size(); i++) {
            averageRewardSeries.add(i, rewards.get(i));
        }
        XYSeries percentageSeries = new XYSeries("percentageOptimal");
        for(int i=0; i < rewards.size(); i++) {
            percentageSeries.add(i, percentages.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(averageRewardSeries);
        dataset.addSeries(percentageSeries);
        return dataset;
    }

    private List<XYSeriesCollection> createXYDataSet(List<PolicyPlotterInput> inputs)
    {
        List<XYSeriesCollection> datasets = new ArrayList<>(2);
        XYSeriesCollection averageRewardsSeriesCollectiion = new XYSeriesCollection();
        XYSeriesCollection averagePercentageSeriesCollection = new XYSeriesCollection();
        for(PolicyPlotterInput input : inputs)
        {
            XYSeries averageRewardSeries = new XYSeries("averageRewards "+ input.getPolicyName());
            for(int i=0; i < input.getAverageRewardsPerStep().size(); i++) {
                averageRewardSeries.add(i, input.getAverageRewardsPerStep().get(i));
            }
            XYSeries percentageSeries = new XYSeries("percentageOptimal " + input.getPolicyName());
            for(int i=0; i < input.getOptimalChoicePercentage().size(); i++) {
                percentageSeries.add(i, input.getOptimalChoicePercentage().get(i));
            }
            averageRewardsSeriesCollectiion.addSeries(averageRewardSeries);
            averagePercentageSeriesCollection.addSeries(percentageSeries);
        }
        datasets.add(averageRewardsSeriesCollectiion);
        datasets.add(averagePercentageSeriesCollection);
        return datasets;
    }

    public static void main( String[ ] args ) {
        TestClass chart = new TestClass(
                "School Vs Years" ,
                "Numer of Schools vs years");

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }

    public static void printChart(ArrayList<Double> rewards, ArrayList<Double> percentages)
    {

        LineChartPlotter chart = new LineChartPlotter(
                "N arm bandit" ,
                "comparision of policies",
                rewards,
                percentages);

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }



    public static void printChart(List<PolicyPlotterInput> inputs)
    {

        LineChartPlotter chart = new LineChartPlotter(
                "N arm bandit" ,
                "comparision of policies",
                inputs);

        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }

}

