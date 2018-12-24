package Service;

import DAO.CategoryDAO;
import DAO.RecordDAO;
import Entity.Category;
import Entity.Record;

import java.util.List;

public class RecordService {
    public static RecordDAO recordDAO = new RecordDAO();

    //查询全部
    public static List<Record> list(){
        List<Record> records = recordDAO.list();
        return records;
    }

    //添加
    public static void add(Record record){
        recordDAO.add(record);
    }

    //按分类删除
    public static void deleteByCategory(int cid){
        recordDAO.delete(cid);
    }
}
