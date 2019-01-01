package Service;

import DAO.RecordDAO;
import Entity.Record;
import utils.dateUtil;

import java.util.List;

public class OverviewService {
    //使用DAO取数据作统计，返回上一级需要的显示信息
    public static OverviewInformation getInformation(){
        //OverviewInformation需要参数int spendToday,int spendMonth,int budget,int dayUp,int dayLeft
        RecordDAO dao = RecordService.recordDAO;
        List<Record> recordsToday = dao.listToday();
        List<Record> recordsMonth = dao.listMonth();
        int daysThisMonth = dateUtil.daysThisMonth();
        int daysUp = dateUtil.daysUpToDay();
        float spendToday = 0.0f, spendMonth = 0.0f, budget;
        budget = Float.parseFloat(ConfigService.budgetValue);
        for (Record record : recordsToday){
            spendToday += record.getCost();
        }
        for (Record record : recordsMonth){
            spendMonth += record.getCost();
        }
        return new OverviewInformation((int)spendToday,(int)spendMonth,
                (int)budget,daysUp,daysThisMonth-daysUp);
    }

    public static int getSpendThisMonth(){
        float spendMonth = 0.0f;
        for (Record record:RecordService.recordDAO.listMonth()){
            spendMonth += record.getCost();
        }
        return (int)spendMonth;
    }
    public static int getBudget(){
        float budget = Float.parseFloat(ConfigService.budgetValue);
        return (int)budget;
    }
}
