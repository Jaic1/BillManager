package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class circleProgressBar extends JPanel {
    //
    private int progress;
    private int minProgress = 0;
    private int maxProgress = 100;

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int x = 25, y = 25;
        int width = getWidth() - 50, height = getHeight() - 50;
        if(getWidth()>getHeight()){
            width = height;
            x = (getWidth()-width) / 2;
        } else{
            height = width;
            y = (getHeight()-height) / 2;
        }
        g2.setStroke(new BasicStroke(20.0f));
        g2.setColor(colorUtil.blueColor);
        g2.drawArc(x,y,width,height,0,360);
        g2.setColor(colorUtil.warningColor);
        g2.drawArc(x,y,width,height,90,-(int)(360*(progress*1.0)/100));
    }

    public void setProgress(int progress){
        if(progress<minProgress)this.progress = minProgress;
        else if(progress>maxProgress)this.progress = maxProgress;
        else this.progress = progress;

        this.repaint();
    }

    public void addProgress(int delta){
        if(progress+delta>maxProgress)progress = maxProgress;
        else progress += delta;

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
