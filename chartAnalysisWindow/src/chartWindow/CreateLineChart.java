package chartAnalysisWindow.src.chartWindow;
/**
 * Created by ustc on 2016/12/8.
 */


/**
 * Created by ustc on 2016/12/8.
 */

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class CreateLineChart {

    public  Loadtxt load;

    public CreateLineChart(Loadtxt e){
        this.load = e;
        XYDataset dataset = createXYDataset(this.load);
        //����2������Dataset ����JFreeChart�����Լ�����Ӧ������
        JFreeChart freeChart = createChart(dataset,this.load);
        //����3����JFreeChart����������ļ���Servlet�������
        saveAsFile(freeChart, "analysis\\analysisChart.jpg", 700, 400);
    }
    /**
     * ����JFreeChart LineXY Chart������ͼ��

     public static void main(String[] args) {
     //����1������XYDataset����׼�����ݣ�
     CreateJFreeChartXYline chart = new CreateJFreeChartXYline();
     }
     */
    // ����Ϊ�ļ�
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

    // ����XYDataset����JFreeChart����
    public static JFreeChart createChart(XYDataset dataset,Loadtxt load) {
        // ����JFreeChart����ChartFactory.createXYLineChart
        JFreeChart jfreechart = ChartFactory.createXYLineChart(load.TITLE, // ����
                load.XLABEL, // categoryAxisLabel ��category�ᣬ���ᣬX���ǩ��
                load.YLABEL, // valueAxisLabel��value�ᣬ���ᣬY��ı�ǩ��
                dataset, // dataset
                PlotOrientation.VERTICAL, true, // legend
                false, // tooltips
                false); // URLs

        // ʹ��CategoryPlot���ø��ֲ������������ÿ���ʡ�ԡ�
       /* XYPlot plot = (XYPlot) jfreechart.getPlot();
        // ����ɫ ͸����
        plot.setBackgroundAlpha(0.5f);
        // ǰ��ɫ ͸����
        plot.setForegroundAlpha(0.5f);
        // �������ÿ��Բο�XYPlot��
        */

        XYPlot plot = (XYPlot) jfreechart.getPlot();
        //  XYBarRenderer renderer = (XYBarRenderer)plot.getRenderer();


        plot.setDomainGridlinePaint(Color.blue);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.blue);
        plot.setRangeGridlinesVisible(true);
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setOutlineVisible(true);
        plot.setOutlinePaint(Color.magenta);


        ValueAxis domainAxis = plot.getDomainAxis();
        NumberAxis domainAxis1 = (NumberAxis)plot.getDomainAxis();//x������
        NumberAxis rAxis1 = (NumberAxis)plot.getRangeAxis();
        domainAxis1.setTickUnit(new NumberTickUnit(2));
        ValueAxis rAxis = plot.getRangeAxis();
        domainAxis.setTickLabelPaint(Color.red);//X��ı���������ɫ
        domainAxis.setTickLabelsVisible(true);//X��ı��������Ƿ���ʾ
        domainAxis.setAxisLinePaint(Color.red);//X�������ɫ
        domainAxis.setTickMarksVisible(true);//������Ƿ���ʾ
        domainAxis.setTickMarkOutsideLength(3);//��������ⳤ��
        domainAxis.setTickMarkInsideLength(3);//��������ڳ���
        domainAxis.setTickMarkPaint(Color.red);//�������ɫ
        //domainAxis.setRange(5, 10);



        rAxis.setTickLabelPaint(Color.red);//Y��ı���������ɫ
        rAxis.setTickLabelsVisible(true);//Y��ı��������Ƿ���ʾ
        rAxis.setAxisLinePaint(Color.red);//Y�������ɫ
        rAxis.setTickMarksVisible(true);//������Ƿ���ʾ
        rAxis.setTickMarkOutsideLength(3);//��������ⳤ��
        rAxis.setTickMarkInsideLength(3);//��������ڳ���
        rAxis.setTickMarkPaint(Color.red);//�������ɫ

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

        if(load.Xmin != null && load.Xmax != null){

            domainAxis.setRange(Double.parseDouble(load.Xmin), Double.parseDouble(load.Xmax));
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

        return jfreechart;
    }

    /**
     * ����XYDataset����
     *
     */
    private static XYDataset createXYDataset(Loadtxt load) {
        XYSeries xyseries1 = new XYSeries("");
        double x =0;
        double y =0;
        for (int i=0;i<load.dataX.size();i++){
            // xyseries1.add(1987, 50);
            x =  Double.parseDouble(load.dataX.get(i));
            y =  Double.parseDouble(load.dataY.get(i));
            xyseries1.add(x, y);
        }

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();

        xySeriesCollection.addSeries(xyseries1);

        return xySeriesCollection;
    }
}
