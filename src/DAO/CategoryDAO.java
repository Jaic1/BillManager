package DAO;

import Entity.Category;
import utils.databaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    //增
    public void add(Category category){
        String addSQL = "insert into category values(null,?,?,?,?)";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(addSQL);
        ) {
            ps.setString(1,category.getName());
            ps.setInt(2,category.getNum());
            ps.setFloat(3,category.getSum());
            ps.setFloat(4,category.getUpperBound());
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            if(resultSet.next()){
                category.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删
    public void delete(int id){
        String deleteSQL = "delete from category where id = " + id;
        try(Connection connection = databaseUtil.getConnection();
            Statement s = connection.createStatement();
        ) {
            s.execute(deleteSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(){
        String deleteSQL = "delete from category";
        try(Connection connection = databaseUtil.getConnection();
            Statement s = connection.createStatement();
        ) {
            s.execute(deleteSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //改
    public void update(Category category){
        String updateSQL = "update category set name=?, num=?, sum=?, upperBound=? where id=?";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateSQL);
        ) {
            ps.setString(1,category.getName());
            ps.setInt(2,category.getNum());
            ps.setFloat(3,category.getSum());
            ps.setFloat(4,category.getUpperBound());
            ps.setInt(5,category.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查
    public Category get(int id){
        String getSQL = "select * from category where id = " + id;
        try(Connection connection = databaseUtil.getConnection();
            Statement s = connection.createStatement();
        ) {
            ResultSet resultSet = s.executeQuery(getSQL);
            if(resultSet.next()){
                Category category = new Category();
                category.setId(id);
                category.setName(resultSet.getString("name"));
                category.setNum(resultSet.getInt("num"));
                category.setSum(resultSet.getFloat("sum"));
                category.setUpperBound(resultSet.getFloat("upperBound"));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //按名字查询
    public Category getByName(String name){
        String nameSQL = "select * from category where name = " + name;
        try(Connection connection = databaseUtil.getConnection();
            Statement s = connection.createStatement();
        ) {
            ResultSet resultSet = s.executeQuery(nameSQL);
            if(resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(name);
                category.setNum(resultSet.getInt("num"));
                category.setSum(resultSet.getFloat("sum"));
                category.setUpperBound(resultSet.getFloat("upperBound"));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //分页查询
    public List<Category> list(){
        return list(0,Short.MAX_VALUE);
    }
    public List<Category> list(int start,int count){
        List<Category> categories = new ArrayList<Category>();
        String listSQL = "select * from category order by id desc limit ?,?";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(listSQL);
        ) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setNum(resultSet.getInt("num"));
                category.setSum(resultSet.getFloat("sum"));
                category.setUpperBound(resultSet.getFloat("upperBound"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
