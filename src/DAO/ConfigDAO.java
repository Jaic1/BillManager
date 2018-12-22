package DAO;

import entity.Config;
import utils.databaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigDAO {

    //增
    public void add(Config config){
        String addSQL = "insert into config values(null,?,?)";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(addSQL);
        ) {
            ps.setString(1,config.getKey());
            ps.setString(2,config.getValue());
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            if(resultSet.next()){
                config.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删
    public void delete(int id){
        String deleteSQL = "delete from config where id = " + id;
        try(Connection connection = databaseUtil.getConnection();
            Statement s = connection.createStatement();
        ) {
            s.execute(deleteSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //改
    public void update(Config config){
        String updateSQL = "update config set key_ = ?, value = ? where id = ?";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateSQL);
        ) {
            ps.setString(1,config.getKey());
            ps.setString(2,config.getValue());
            ps.setInt(3,config.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查
    public Config get(int id){
        String getSQL = "select * from config where id = " + id;
        try(Connection connection = databaseUtil.getConnection();
            Statement s = connection.createStatement();
        ) {
            ResultSet resultSet = s.executeQuery(getSQL);
            if(resultSet.next()){
                Config config = new Config();
                config.setId(id);
                config.setKey(resultSet.getString("key_"));
                config.setValue(resultSet.getString("value"));
                return config;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //分页查询
    public List<Config> list(){
        return list(0, Short.MAX_VALUE);
    }
    public List<Config> list(int start,int count){
        List<Config> configs = new ArrayList<Config>();
        String getsSQL = "select * from config order by id desc limit ?,?";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(getsSQL);
        ) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Config config = new Config();
                config.setId(resultSet.getInt(1));
                config.setKey(resultSet.getString(2));
                config.setValue(resultSet.getString(3));
                configs.add(config);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return configs;
    }

    //按键查询
    public Config getByKey(String key){
        String keySQL = "select * from config where key_ = ?";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(keySQL);
        ) {
            ps.setString(1,key);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                Config config = new Config();
                config.setId(resultSet.getInt(1));
                config.setKey(key);
                config.setValue(resultSet.getString(3));
                return config;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
