package utils;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
 
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Service.ReportService;
import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;
 
public class chartUtil {

    public static Image getImage(int width, int height) {
        double[] sampleValues = sampleValues();
        String[] sampleLabels = sampleLabels();
        int max = max(sampleValues);
        Color[] sampleColors = new Color[] { colorUtil.sampleColor };

        BarChart chart = new BarChart();

        //样本和标签
        chart.setSampleCount(sampleValues.length);
        chart.setRange(0, (max + 20)-(max + 20)%10);
        chart.setSampleValues(0, sampleValues);
        chart.setSampleLabels(sampleLabels);
        chart.setSampleColors(sampleColors);
        chart.setSampleLabelsOn(true);
        chart.setSampleLabelStyle(Chart.BELOW);

        //legend
        chart.setLegendOn(true);
        chart.setLegendPosition(Chart.LEFT);
        chart.setLegendLabels(new String[] { "剁手记录" });

        //字体
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));
        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));

        //背景
        chart.setValueLinesOn(false);
        chart.setChartBackground(Color.white);
        chart.setBackground(colorUtil.backgroundColor);

        return chart.getImage(width, height);
    }

    private static String[] sampleLabels() {
        String[] sampleLabels = new String[dateUtil.daysThisMonth()];
        sampleLabels[0] = "1日";
        sampleLabels[6] = "7日";
        sampleLabels[14] = "15日";
        sampleLabels[22] = "23日";
        sampleLabels[sampleLabels.length-2] = String.valueOf(sampleLabels.length) + "日";
        return sampleLabels;
    }

    private static double[] sampleValues() {
        return ReportService.listThisMonthSpend();
    }

    public static int max(double[] sampleValues) {
        int max = 0;
        for (double v : sampleValues) {
            if (v > max)
                max = (int) v;
        }
        return max;

    }
 
    public static void main(String[] args) {
        JLabel l = new JLabel();
        Image img = chartUtil.getImage(400, 300);
        Icon icon = new ImageIcon(img);
        l.setIcon(icon);
        GUIUtil.testComponent(l,0.8f);
    }
}