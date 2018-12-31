package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class circleProgressBar extends JPanel {

    private int progress = 0;
    private int minProgress = 0;
    private int maxProgress = 100;
    private String progressText = "0%";
    private Color fontColor = colorUtil.fontColor;

    public void paint(Graphics g){
        super.paint(g);
        progressText = String.valueOf(progress*100 / maxProgress) + "%";
        if(progress > maxProgress)fontColor = colorUtil.warningColor;
        else fontColor = colorUtil.fontColor;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int x = 15, y = 15;
        int width = getWidth() - 30, height = getHeight() - 30;
        int fontSize = 0;
        if(getWidth() > getHeight()){
            width = height;
            x = (getWidth()-width) / 2;
            fontSize = height / 8;
        } else{
            height = width;
            y = (getHeight()-height) / 2;
            fontSize = width / 8;
        }
        g2.setStroke(new BasicStroke(20.0f));
        g2.setColor(colorUtil.blueColor);
        g2.drawArc(x,y,width,height,0,360);
        g2.setColor(colorUtil.getByProgress(progress));
        g2.drawArc(x,y,width,height,90,-(int)(360*(progress*1.0)/100));
        if(progress > 100){
            g2.setColor(colorUtil.getByProgressExceed(progress));
            g2.drawArc(x,y,width,height,90,-(int)(360*((progress-100)*1.0)/100));
        }

        g2.setFont(new Font("黑体",Font.BOLD,fontSize));
        FontMetrics fontMetrics = g2.getFontMetrics();
        int fontWidth = fontMetrics.stringWidth(progressText);
        int fontAscent = fontMetrics.getAscent();
        g2.setColor(fontColor);
        g2.drawString(progressText,(getWidth()-fontWidth)/2,(getHeight()+fontAscent)/2);
    }

    public void setProgress(int progress){
        this.progress = progress;
        this.repaint();
    }

    public void addProgress(int delta){
        progress += delta;
        this.repaint();
    }

    public static void main(String[] args) {
        JPanel p = new JPanel();
        circleProgressBar cpb = new circleProgressBar();
        cpb.setProgress(0);
        JButton b = new JButton("+1");
        p.setLayout(new BorderLayout());
        p.add(cpb, BorderLayout.CENTER);
        p.add(b, BorderLayout.SOUTH);
        GUIUtil.testComponent(p, 1.0f);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cpb.addProgress(1);
            }
        });
    }
}
