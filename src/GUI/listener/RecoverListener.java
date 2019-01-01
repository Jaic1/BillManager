package GUI.listener;

import GUI.panel.configPanel;
import GUI.panel.mainPanel;
import GUI.panel.recoverPanel;
import Service.ConfigService;
import javafx.stage.FileChooser;
import utils.databaseUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecoverListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        recoverPanel panel = recoverPanel.instance;
        String mysqlPath = ConfigService.get(ConfigService.mysqlPath);
        if(mysqlPath == null||mysqlPath.length() == 0){
            JOptionPane.showMessageDialog(panel,"请先配置MYSQL路径呢");
            mainPanel.instance.workingPanel.show(configPanel.instance);
            configPanel.instance.tMysqlPath.grabFocus();
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(databaseUtil.desktopDir));
        fileChooser.setSelectedFile(new File(databaseUtil.databaseName + ".sql"));
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".sql");
            }

            @Override
            public String getDescription() {
                return ".sql";
            }
        });
        int returnValue = fileChooser.showOpenDialog(panel);
        File file = fileChooser.getSelectedFile();
        System.out.println(file);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            try{
                databaseUtil.recover(mysqlPath,file.getAbsolutePath());
                JOptionPane.showMessageDialog(panel,"恢复成功");
            }catch (Exception e1){
                e1.printStackTrace();
                JOptionPane.showMessageDialog(panel,"恢复失败,\r\n错误信息:"+e1.getMessage());
            }
        }
    }
}
