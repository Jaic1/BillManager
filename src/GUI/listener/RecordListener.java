package GUI.listener;

import Entity.Category;
import Entity.Record;
import GUI.panel.categoryPanel;
import GUI.panel.mainPanel;
import GUI.panel.overviewPanel;
import GUI.panel.recordPanel;
import Service.ConfigService;
import Service.OverviewInformation;
import Service.OverviewService;
import Service.RecordService;
import jdk.nashorn.internal.scripts.JO;
import utils.GUIUtil;
import utils.dateUtil;

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

        //安全模式下消费不能超过单笔上限 及 提醒该笔消费超过日均可用，不能透支
        if(ConfigService.modeValue.equals("安全模式")){
            if((float)spend > category.getUpperBound()) {
                JOptionPane.showMessageDialog(panel,
                        category.getName() + "单笔消费不能超过" + category.getUpperBound() + "元");
                return;
            }
            int spendMonth = OverviewService.getSpendThisMonth();
            int budget = OverviewService.getBudget();
            if(spend + spendMonth > budget){
                JOptionPane.showMessageDialog(panel,"只剩下￥"+(budget-spendMonth)+"了，不能透支哦");
                return;
            }
            int dayLeft = dateUtil.daysThisMonth() - dateUtil.daysUpToDay();
            if(dayLeft == 0)dayLeft = 1;
            if(spend > (budget-spendMonth)/dayLeft){
                if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(panel,
                        "该笔消费￥"+spend+"超过剩余日均可用￥"+(budget-spendMonth)/dayLeft+"，是否继续？")){
                    return;
                }
            }
        }
        if(ConfigService.modeValue.equals("自由模式")){
            if((float)spend > category.getUpperBound()) {
                if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(panel,
                        category.getName() + "单笔消费不能超过" + category.getUpperBound() + "元，是否继续")) {
                    return;
                }
            }
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
