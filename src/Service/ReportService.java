package Service;

import Entity.Record;
import utils.dateUtil;

import java.util.Date;
import java.util.List;

public class ReportService {
    public static double[] listThisMonthSpend(){
        List<Record> records = RecordService.recordDAO.listMonth();
        double[] sampleValues = new double[dateUtil.daysThisMonth()];
        Date start = dateUtil.monthStart();
        for (int i = 0; i < sampleValues.length; i++) {
            sampleValues[i] = 0.0;
        }
        for (Record record:records){
            int delta = (int)((record.getDate().getTime() - start.getTime()) / dateUtil.msOfDay);
            sampleValues[delta] += record.getCost();
        }
        return sampleValues;
    }
}
