package com.example.monitor;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;

public class LineGraph {

	private GraphicalView view;

	private TimeSeries datasetPresure = new TimeSeries("Blood Presure");
	private TimeSeries datasetRate = new TimeSeries("Heart beat Rate");
	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

	private XYSeriesRenderer rendererPresure = new XYSeriesRenderer(); // This
																		// will
																		// be
																		// used
																		// to
																		// customize
																		// line
																		// 1
	private XYSeriesRenderer rendererRate = new XYSeriesRenderer();
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds
																					// a
																					// collection
																					// of
																					// XYSeriesRenderer
																					// and
																					// customizes
																					// the
																					// graph

	public LineGraph() {
		// Add single dataset to multiple dataset
		mDataset.addSeries(datasetPresure);
		mDataset.addSeries(datasetRate);

		// Customization time for line 1!
		rendererPresure.setColor(Color.MAGENTA);
		rendererPresure.setPointStyle(PointStyle.CIRCLE);
		rendererPresure.setFillPoints(true);

		// Customization time for line 1!
		rendererRate.setColor(Color.RED);
		rendererRate.setPointStyle(PointStyle.SQUARE);
		rendererRate.setFillPoints(true);

		// Enable Zoom
		// mRenderer.setZoomButtonsVisible(true);

		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.TRANSPARENT);
		mRenderer.setXTitle("Seconds #");
		mRenderer.setYTitle("Beats/min & mmHg ");
		mRenderer.setGridColor(Color.CYAN);
		mRenderer.setXAxisMax(60.0);
		// mRenderer.setYAxisMax(32770.0);
		mRenderer.setChartTitle("Heart Monitor");

		// Add single renderer to multiple renderer
		mRenderer.addSeriesRenderer(rendererPresure);
		mRenderer.addSeriesRenderer(rendererRate);
		}

	public GraphicalView getView(Context context) {
		view = ChartFactory.getLineChartView(context, mDataset, mRenderer);
		return view;
	}

	public void addNewPointsPresure(Point p) {
		datasetPresure.add(p.getX(), p.getY());
	}
	public void addNewPointsRate(int x, int y) {
		datasetRate.add(x,y);
	}

}
