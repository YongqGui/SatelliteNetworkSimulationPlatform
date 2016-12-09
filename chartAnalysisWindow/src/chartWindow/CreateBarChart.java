package chartAnalysisWindow.src.chartWindow;

/**
 * Created by ustc on 2016/12/8.
 */

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.*;
import org.jfree.experimental.chart.demo.CombinedCategoryPlotDemo1;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
public class  CreateBarChart{
    public  Loadtxt load;
    private IntervalXYDataset dataset;


    public CreateBarChart(Loadtxt e){
        this.load = e;
        //IntervalXYDataset dataset = createXYDataset(this.load);
        this.dataset = createXYDataset(this.load);
        //����2������Dataset ����JFreeChart�����Լ�����Ӧ������
        JFreeChart freeChart = createChart(this.dataset,this.load);

        //����3����JFreeChart����������ļ���Servlet�������
        saveAsFile(freeChart, "analysis\\analysisChart.jpg", 700, 400);
    }

    public static void saveAsFile(JFreeChart chart, String outputPath,
                                  int weight, int height) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            // ����ΪPNG
            ChartUtilities.writeChartAsPNG(out, chart, weight, height);
            // ����ΪJPEG
            // ChartUtilities.writeChartAsJPEG(out, chart, 500, 400);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    public static JPanel createDemoPanel(JFreeChart chart) {
        //JFreeChart chart = createChart();
        return new ChartPanel(chart);
    }

    public static JFreeChart createChart(IntervalXYDataset dataset,Loadtxt load) {
        // ����JFreeChart����ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createXYBarChart(load.TITLE, // ����
                load.XLABEL, // categoryAxisLabel ��category�ᣬ���ᣬX���ǩ��
                false, // valueAxisLabel��value�ᣬ���ᣬY��ı�ǩ��
                load.YLABEL,
                dataset, // dataset
                PlotOrientation.VERTICAL, true, // legend
                false, // tooltips
                false); // URLs
        double value =0.6;
        if (load.Margin != null){
            value =  Double.parseDouble(load.Margin );
        }
        XYPlot plot = (XYPlot) jfreechart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer)plot.getRenderer();

        NumberAxis domainAxis1 = (NumberAxis)plot.getDomainAxis();//x������
        NumberAxis rAxis1 = (NumberAxis)plot.getRangeAxis();//Y������
       // domainAxis1.setTickUnit(new NumberTickUnit(2));
        
        plot.setDomainGridlinePaint(Color.blue);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.blue);
        plot.setRangeGridlinesVisible(true);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setOutlineVisible(true);
        plot.setOutlinePaint(Color.magenta);
        renderer.setBaseOutlinePaint(Color.ORANGE);
        renderer.setDrawBarOutline(true);
        renderer.setMargin(value);

        ValueAxis domainAxis = plot.getDomainAxis();
        ValueAxis rAxis = plot.getRangeAxis();
        domainAxis.setTickLabelPaint(Color.red);//X��ı���������ɫ
        domainAxis.setTickLabelsVisible(true);//X��ı��������Ƿ���ʾ
        domainAxis.setAxisLinePaint(Color.red);//X�������ɫ
        domainAxis.setTickMarksVisible(true);//������Ƿ���ʾ
        domainAxis.setTickMarkOutsideLength(3);//��������ⳤ��
        domainAxis.setTickMarkInsideLength(3);//��������ڳ���
        domainAxis.setTickMarkPaint(Color.red);//�������ɫ
        // domainAxis.setRange(5, 10);

        rAxis.setTickLabelPaint(Color.red);//Y��ı���������ɫ
        rAxis.setTickLabelsVisible(true);//Y��ı��������Ƿ���ʾ
        rAxis.setAxisLinePaint(Color.red);//Y�������ɫ
        rAxis.setTickMarksVisible(true);//������Ƿ���ʾ
        rAxis.setTickMarkOutsideLength(3);//��������ⳤ��
        rAxis.setTickMarkInsideLength(3);//��������ڳ���
        rAxis.setTickMarkPaint(Color.red);//�������ɫ
        rAxis.setTickMarkInsideLength(3);//��̶������ڳ���
        rAxis.setTickMarkPaint(Color.red);//�̶�����ɫ
        rAxis.setTickLabelsVisible(true);//�̶���ֵ�Ƿ���ʾ
        // ����Y������Ƿ���ʾ�����ǰ������rAxis.setMinorTickMarksVisible(true); ����������ʾ��
        rAxis.setTickMarksVisible(true);
        rAxis.setAxisLinePaint(Color.red);//Y��������ɫ
        rAxis.setAxisLineVisible(true);//Y�������Ƿ���ʾ
        //������ߵ�һ�� Item ��ͼƬ���˵ľ��� (������rAxis.setRange(100, 600);����²�������)
        rAxis.setUpperMargin(0.15);
        //������͵�һ�� Item ��ͼƬ�׶˵ľ���
        rAxis.setLowerMargin(0.15);
        rAxis.setAutoRange(true);//�Ƿ��Զ���Ӧ��Χ
        rAxis.setVisible(true);//Y�������Ƿ���ʾ

        //���þ���ͼƬ��˾���
        domainAxis.setUpperMargin(0.2);
        //���þ���ͼƬ�Ҷ˾���
        domainAxis.setLowerMargin(0.2);
        //�����ᾫ��
        NumberAxis na = (NumberAxis) plot.getRangeAxis();
        na.setAutoRangeIncludesZero(true);
        //  DecimalFormat df = new DecimalFormat("#0.000");
        //���������ݱ�ǩ����ʾ��ʽ
        //  na.setNumberFormatOverride(df);
        //��������͸����
        plot.setForegroundAlpha(1.0f);

        System.out.print(load.Xmin +"dddddddd");
        if(load.Xmin != null && load.Xmax != null){

            domainAxis.setRange(Double.parseDouble(load.Xmin), Double.parseDouble(load.Xmax));
            System.out.print("load.Xmin");
        }
        if(load.Ymin != null && load.Ymax != null){

            rAxis.setRange(Double.parseDouble(load.Ymin), Double.parseDouble(load.Ymax));
        }
        if(load.Xunit != null){
            domainAxis1.setTickUnit(new NumberTickUnit(Double.parseDouble(load.Xunit)));
        }
        if(load.Yunit != null){
            rAxis1.setTickUnit(new NumberTickUnit(Double.parseDouble(load.Yunit)));
        }
        plot.setRenderer(renderer);
        return jfreechart;
    }

    /**
     * ����XYDataset����
     *
     */
    private static XYSeriesCollection createXYDataset(Loadtxt load) {
        XYSeries xyseries1 = new XYSeries("");
        double x =0;
        double y =0;
        for (int i=0;i<load.dataX.size();i++){
            // xyseries1.add(1987, 50);
            x =  Double.parseDouble(load.dataX.get(i));
            y =  Double.parseDouble(load.dataY.get(i));
            xyseries1.add(x,y);
        }
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(xyseries1);
        return xySeriesCollection;
    }
}
