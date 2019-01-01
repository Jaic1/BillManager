package Service;

import DAO.ConfigDAO;
import Entity.Config;

public class ConfigService {
    public static final String budget = "budget";
    public static final String budgetDefaultValue = "0";
    public static final String mode = "mode";
    public static final String modeDefaultValue = "安全模式";
    public static final String mysqlPath = "mysqlPath";
    public static final String mysqlPathDefaultValue = "C:\\Program Files\\MySQL\\MySQL Server 5.5";
    public static String budgetValue;
    public static String modeValue;
    public static String mysqlPathValue;
    public static ConfigDAO configDAO = new ConfigDAO();


    //初始化+刷新时查询
    public static void init(){
        budgetValue = init(budget,budgetDefaultValue);
        modeValue = init(mode,modeDefaultValue);
        mysqlPathValue = init(mysqlPath,mysqlPathDefaultValue);
    }
    private static String init(String key,String value){
        Config config = configDAO.getByKey(key);
        if(config == null){
            config = new Config();
            config.setKey(key);
            config.setValue(value);
            configDAO.add(config);
        }
        return config.getValue();
    }

    //更新
    public static void update(String key,String value){
        Config config = configDAO.getByKey(key);
        if(config != null){
            config.setValue(value);
            configDAO.update(config);
            switch (key){
                case "budget":budgetValue = value;break;
                case "mode":modeValue = value;break;
                case "mysqlPath":mysqlPathValue = value;break;
            }
        }
    }

    //重置
    public static void resetWithCategoryConserved(){
        RecordService.recordDAO.deleteThisMonth();
        CategoryService.reset();
        update(budget,budgetDefaultValue);
        update(mode,modeDefaultValue);
        update(mysqlPath,mysqlPathDefaultValue);
    }
    public static void resetWithoutCategoryConserved(){
        RecordService.recordDAO.delete();
        CategoryService.categoryDAO.delete();
        update(budget,budgetDefaultValue);
        update(mode,modeDefaultValue);
        update(mysqlPath,mysqlPathDefaultValue);
    }

    //按关键字查询值
    public static String get(String key){
        Config config = configDAO.getByKey(key);
        return config.getValue();
    }
}
