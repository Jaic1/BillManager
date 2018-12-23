package Service;

import DAO.ConfigDAO;
import Entity.Config;

public class ConfigService {
    public static final String budget = "budget";
    public static final String budgetDefaultValue = "0";
    public static final String mode = "model";
    public static final String modeDefaultValue = "安全模式";
    public static final String mysqlPath = "mysqlPath";
    public static final String mysqlPathDefaultValue = "C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysql.exe";
    public static ConfigDAO configDAO = new ConfigDAO();


    //初始化
    static {
        init();
    }
    public static void init(){
        init(budget,budgetDefaultValue);
        init(mode,modeDefaultValue);
        init(mysqlPath,mysqlPathDefaultValue);
    }
    private static void init(String key,String value){
        Config config = configDAO.getByKey(key);
        if(config == null){
            config = new Config();
            config.setKey(key);
            config.setValue(value);
            configDAO.add(config);
        }
    }

    //更新
    public void update(String key,String value){
        Config config = configDAO.getByKey(key);
        if(config != null){
            config.setValue(value);
            configDAO.update(config);
        }
    }

    //重置
    public void reset(){
        update(budget,budgetDefaultValue);
        update(mode,modeDefaultValue);
        update(mysqlPath,mysqlPathDefaultValue);
    }

    //按关键字查询值
    public String get(String key){
        Config config = configDAO.getByKey(key);
        return config.getValue();
    }
}