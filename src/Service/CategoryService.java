package Service;

import DAO.CategoryDAO;
import DAO.RecordDAO;
import Entity.Category;

import java.util.Collections;
import java.util.List;

public class CategoryService {
    RecordDAO recordDAO = new RecordDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    //理想的用法是：
    //每次new一个CategoryService
    //然后用list()

    //查询全部
    public List<Category> list(){
        List<Category> cs = categoryDAO.list();
        Collections.sort(cs,(c1,c2)->c2.getNum()-c1.getNum());
        return cs;
    }

    //增
    public void add(Category category){
        categoryDAO.add(category);
    }
    //按名字默认添加
    public void add(String name){
        Category category = new Category();
        category.setName(name);
        category.setNum(0);
        category.setSum(0.0f);
        category.setUpperBound(0.0f);
        categoryDAO.add(category);
    }

    //改
    public void update(Category category){
        categoryDAO.update(category);
    }

    //删
    public void delete(int id){
        categoryDAO.delete(id);
    }
}
