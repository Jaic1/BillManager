package startup;

import GUI.frame.mainFrame;
import GUI.panel.mainPanel;
import GUI.panel.overviewPanel;
import utils.GUIUtil;

import javax.swing.*;

public class Bootstrap {
    public static void main(String[] args) throws Exception{
        GUIUtil.useLNF();

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                mainFrame.instance.setVisible(true);
                mainPanel.instance.workingPanel.show(overviewPanel.instance);
            }
        });
    }
}
