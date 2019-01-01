package GUI.panel;

import sun.java2d.pipe.BufferedBufImgOps;
import utils.GUIUtil;
import utils.chartUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class reportPanel extends workingPanelAbstractClass {
    static {
        GUIUtil.useLNF();
    }
    public static reportPanel instance = new reportPanel();

    public JLabel label = new JLabel();

    private reportPanel(){
        this.setLayout(new BorderLayout());
        this.add(label);
    }

    @Override
    public void updateData() {
        //System.out.println("why it don't show the first time");
        Image img = chartUtil.getImage(this.getWidth(),this.getHeight());
        ImageIcon icon = new ImageIcon(img);
        label.setIcon(icon);
    }

    @Override
    public void addListener() {

    }

    public static void main(String[] args) {
        GUIUtil.testComponent(reportPanel.instance,1.0f);
    }
}
