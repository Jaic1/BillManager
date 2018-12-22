package GUI.frame;

import GUI.panel.mainPanel;

import javax.swing.*;

public class mainFrame extends JFrame {
    public static mainFrame instance = new mainFrame();

    private mainFrame(){
        this.setSize(500,450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel.instance);
    }

    public static void main(String[] args) {
        mainFrame.instance.setVisible(true);
    }
}
