package utils;

import java.util.Calendar;
import java.util.Date;

public class dateUtil {
    public static final long msOfDay = 24*60*60*1000;

    public static java.sql.Date util2sql(java.util.Date date){
        return new java.sql.Date(date.getTime());
    }

    //当日
    public static Date today(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    //当月开始日期
    public static Date monthStart(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DATE,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        return c.getTime();
    }

    //当月结束日期
    public static Date monthEnd(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.DATE,1);
        c.add(Calendar.MONTH,1);
        c.add(Calendar.DATE,-1);
        return c.getTime();
    }

    //当月天数
    public static int daysThisMonth(){
        long lastMilliSeconds = monthEnd().getTime();
        long firstMilliSeconds = monthStart().getTime();
        return (int)((lastMilliSeconds - firstMilliSeconds) / msOfDay) + 1;
    }

    //当月已过天数
    public static int daysUpToDay(){
        long nowMilliSeconds = today().getTime();
        long firstMilliSeconds = monthStart().getTime();
        return (int)((nowMilliSeconds - firstMilliSeconds) / msOfDay) + 1;
    }

    public static void main(String[] args) {
        System.out.println(dateUtil.monthEnd());
    }
}
