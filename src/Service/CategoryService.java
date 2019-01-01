package Service;

import DAO.CategoryDAO;
import DAO.RecordDAO;
import Entity.Category;
import Entity.Record;

import java.util.Collections;
import java.util.List;

public class CategoryService {
    public static CategoryDAO categoryDAO = new CategoryDAO();

    //查询全部
    public static List<Category> list(){
        List<Category> cs = categoryDAO.list();
        if(RecordService.recordDAO.listMonth().isEmpty()){
            for(Category category:cs){
                category.setNum(0);
                category.setSum(0.0f);
                categoryDAO.update(category);
            }
        }
        else Collections.sort(cs,(c1,c2)->c2.getNum()-c1.getNum());
        return cs;
    }

    //增
    public static void add(Category category){
        categoryDAO.add(category);
    }
    //按名字和单笔上限默认添加
    public static void add(String name,float upperBound){
        Category category = new Category();
        category.setName(name);
        category.setNum(0);
        category.setSum(0.0f);
        category.setUpperBound(upperBound);
        categoryDAO.add(category);
    }

    //按cid更新相应分类信息，当新增一笔记录时
    public static void update(int cid,float delta){
        Category category = categoryDAO.get(cid);
        try{
            category.addNum(1);
            category.addSum(delta);
            categoryDAO.update(category);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //重置所有分类但不删除(是在Service层还是DAO层写？)
    public static void reset(){
        List<Category> cs = categoryDAO.list();
        for(Category category:cs){
            category.setNum(0);
            category.setSum(0.0f);
            categoryDAO.update(category);
        }
    }

    //改
    public static void update(Category category){
        categoryDAO.update(category);
    }

    //删
    public static void delete(int id){
        categoryDAO.delete(id);
    }
    //按分类删除
    public static void deleteRecordByCategory(int cid){
        RecordService.recordDAO.deleteByCategory(cid);
    }
}
