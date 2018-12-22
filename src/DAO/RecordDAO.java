package DAO;

import com.sun.org.apache.regexp.internal.RE;
import entity.Record;
import utils.databaseUtil;
import utils.dateUtil;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordDAO {

    //增
    public void add(Record record){
        String addSQL = "insert into record values(null,?,?,?,?)";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(addSQL);
        ) {
           ps.setInt(1,record.getCid());
           ps.setFloat(2,record.getCost());
           ps.setString(3,record.getComment());
           ps.setDate(4, dateUtil.util2sql(record.getDate()));
           ps.execute();
           ResultSet resultSet = ps.getGeneratedKeys();
           if(resultSet.next()){
               record.setId(resultSet.getInt(1));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删
    public void delete(int id){
        String deleteSQL = "delete from record where id = " + id;
        try(Connection connection = databaseUtil.getConnection();
            Statement s = connection.createStatement();
        ) {
            s.execute(deleteSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //改
    public void update(Record record){
        String updateSQL = "update record set cid=?, cost=?, comment=?, date=? where id=?";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateSQL);
        ) {
            ps.setInt(1,record.getCid());
            ps.setFloat(2,record.getCost());
            ps.setString(3,record.getComment());
            ps.setDate(4,dateUtil.util2sql(record.getDate()));
            ps.setInt(5,record.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查
    public Record get(int id){
        String getSQL = "select * from record where id = " + id;
        try(Connection connection = databaseUtil.getConnection();
            Statement s = connection.createStatement();
        ) {
            ResultSet resultSet = s.executeQuery(getSQL);
            if(resultSet.next()){
                Record record = new Record();
                record.setId(resultSet.getInt("id"));
                record.setCid(resultSet.getInt("cid"));
                record.setCost(resultSet.getFloat("cost"));
                record.setComment(resultSet.getString("comment"));
                record.setDate(resultSet.getDate("date"));
                return record;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //分页查询
    public List<Record> list(){return list(0,Short.MAX_VALUE);}
    public List<Record> list(int start,int count){
        List<Record> records = new ArrayList<Record>();
        String listSQL = "select * from record order by id desc limit ?,?";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(listSQL);
        ) {
            ps.setInt(1,start);
            ps.setInt(2,count);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Record record = new Record();
                record.setId(resultSet.getInt("id"));
                record.setCid(resultSet.getInt("cid"));
                record.setCost(resultSet.getFloat("cost"));
                record.setComment(resultSet.getString("comment"));
                record.setDate(resultSet.getDate("date"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    //按类查询
    public List<Record> getByKey(int cid){
        List<Record> records = new ArrayList<Record>();
        String cidSQL = "select * from record where cid = " + cid;
        try(Connection connection = databaseUtil.getConnection();
            Statement s = connection.createStatement();
        ) {
            ResultSet resultSet = s.executeQuery(cidSQL);
            while (resultSet.next()){
                Record record = new Record();
                record.setId(resultSet.getInt("id"));
                record.setCid(cid);
                record.setCost(resultSet.getFloat("cost"));
                record.setComment(resultSet.getString("comment"));
                record.setDate(resultSet.getDate("date"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    //按日查询
    public List<Record> listToday(){
        return listDay(dateUtil.today());
    }
    public List<Record> listDay(Date day){
        List<Record> records = new ArrayList<Record>();
        String listDaySQL = "select * from record where date = ?";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(listDaySQL);
        ) {
            ps.setDate(1,dateUtil.util2sql(day));
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Record record = new Record();
                record.setId(resultSet.getInt("id"));
                record.setCid(resultSet.getInt("cid"));
                record.setCost(resultSet.getFloat("cost"));
                record.setComment(resultSet.getString("comment"));
                record.setDate(day);
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    //按月查询
    public List<Record> listTomonth(){
        return listDays(dateUtil.monthStart(),dateUtil.monthEnd());
    }
    public List<Record> listDays(Date start,Date end){
        List<Record> records = new ArrayList<Record>();
        String listDaysSQL = "select * from record where date>=? and date<=?";
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(listDaysSQL);
        ) {
            ps.setDate(1,dateUtil.util2sql(start));
            ps.setDate(2,dateUtil.util2sql(end));
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Record record = new Record();
                record.setId(resultSet.getInt("id"));
                record.setCid(resultSet.getInt("cid"));
                record.setCost(resultSet.getFloat("cost"));
                record.setComment(resultSet.getString("comment"));
                record.setDate(resultSet.getDate("date"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}
