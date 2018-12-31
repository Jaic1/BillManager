package GUI.panel;

import Service.OverviewInformation;
import Service.OverviewService;
import utils.GUIUtil;
import utils.circleProgressBar;
import utils.colorUtil;

import javax.swing.*;
import java.awt.*;

public class overviewPanel extends workingPanelAbstractClass{
    static {
        GUIUtil.useLNF();
    }
    public static overviewPanel instance = new overviewPanel();

    public circleProgressBar bar = new circleProgressBar();

    public JLabel lSpendDay = new JLabel("今日消费");
    public JLabel lSpendAverageByDay = new JLabel("日均消费");
    public JLabel lSpendMonth = new JLabel("本月消费");
    public JLabel lLeftMonth = new JLabel("本月剩余");
    public JLabel lLeftAverageByDay = new JLabel("日均可用");
    public JLabel lLeftDay = new JLabel("距离月末");

    public JLabel vSpendDay = new JLabel();
    public JLabel vSpendAverageByDay = new JLabel();
    public JLabel vSpendMonth = new JLabel();
    public JLabel vLeftMonth = new JLabel();
    public JLabel vLeftAverageByDay = new JLabel();
    public JLabel vDayLeft = new JLabel();

    private int width;
    private int height;

    private overviewPanel(){
        this.setLayout(new BorderLayout());
        width = this.getWidth();
        height = this.getHeight();
        setColor();

        this.add(centerPart(), BorderLayout.CENTER);
        this.add(southPart(), BorderLayout.SOUTH);
    }

    private JPanel centerPart(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(centerPartCenter(), BorderLayout.CENTER);
        p.add(centerPartwest(), BorderLayout.WEST);
        return p;
    }
    private Component centerPartCenter(){
        return bar;
    }
    private Component centerPartwest(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4,1));
        p.add(lSpendDay);
        p.add(vSpendDay);
        p.add(lSpendAverageByDay);
        p.add(vSpendAverageByDay);
        return p;
    }

    private JPanel southPart(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,4));
        p.add(lSpendMonth);
        p.add(lLeftMonth);
        p.add(lLeftAverageByDay);
        p.add(lLeftDay);
        p.add(vSpendMonth);
        p.add(vLeftMonth);
        p.add(vLeftAverageByDay);
        p.add(vDayLeft);
        return p;
    }

    private void setColor(){
        GUIUtil.setColor(colorUtil.geryColor, lSpendDay, lSpendAverageByDay, lSpendMonth,
                lLeftMonth, lLeftAverageByDay, lLeftDay);
        GUIUtil.setColor(colorUtil.fontColor, vSpendDay, vSpendAverageByDay, vSpendMonth,
                vLeftMonth, vLeftAverageByDay, vDayLeft);
    }

    @Override
    public void updateData() {
        OverviewInformation info = OverviewService.getInformation();
        vSpendDay.setText(info.spendToday);
        vSpendAverageByDay.setText(info.spendAvg);
        vSpendMonth.setText(info.spendMonth);
        vLeftMonth.setText(info.leftMonth);
        vLeftAverageByDay.setText(info.leftByDay);
        vDayLeft.setText(info.dayLeft);
        if(info.isOverSpend){
            GUIUtil.setColor(colorUtil.warningColor, vLeftMonth);
        }
        else {
            GUIUtil.setColor(colorUtil.fontColor, vLeftMonth);
        }
        //环形进度条
        bar.setProgress(info.usePercentage);
    }

    @Override
    public void addListener() {

    }

    public static void main(String[] args) {
        GUIUtil.testComponent(overviewPanel.instance, 0.8f);
    }
}
