package GUI.listener;

import Entity.Category;
import Entity.Record;
import GUI.panel.categoryPanel;
import GUI.panel.mainPanel;
import GUI.panel.overviewPanel;
import GUI.panel.recordPanel;
import Service.ConfigService;
import Service.RecordService;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        recordPanel panel = recordPanel.instance;
        if(panel.categoryModel.getSize() == 0){
            JOptionPane.showMessageDialog(panel,"主人还没添加分类呢");
            mainPanel.instance.workingPanel.show(categoryPanel.instance);
            return;
        }
        if(!GUIUtil.checkZero(panel.spend,panel.lSpend.getText())){
            return;
        }
        int spend = Integer.parseInt(panel.spend.getText());
        Category category = panel.getSelectedCategory();
        if(category == null){
            JOptionPane.showMessageDialog(panel,"请选择分类哦");
            return;
        }
        if(ConfigService.modeValue.equals("安全模式")){
            if((float)spend > category.getUpperBound()){
                JOptionPane.showMessageDialog(panel,
                        category.getName()+"单笔消费不能超过"+category.getUpperBound()+"元");
                return;
            }
            //安全模式下消费不能超过预支及更多提醒功能
        }
        Record record = new Record();
        record.setCid(category.getId());
        record.setCost((float)spend);
        record.setComment(panel.comment.getText());
        record.setDate(panel.datepick.getDate());
        RecordService.add(record);
        JOptionPane.showMessageDialog(panel,"又剁一次");
        mainPanel.instance.workingPanel.show(overviewPanel.instance);
    }
}
