package Service;

import DAO.CategoryDAO;
import DAO.RecordDAO;
import Entity.Category;
import Entity.Record;

import java.util.List;

public class RecordService {
    RecordDAO recordDAO = new RecordDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    //查询全部
    public List<Record> list(){
        List<Record> records = recordDAO.list();
        return records;
    }

    //添加
    public void add(Record record){
        recordDAO.add(record);
    }

    //按分类删除
    public void deleteByCategory(int cid){
        recordDAO.delete(cid);
    }
}
