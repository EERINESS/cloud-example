package test;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by zzq on 2020/5/21.
 */
public class DateTest {
    @Test
    public void test1(){
        String UTC = "20200526075903";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date UtcDate;
        try {
            UtcDate = format.parse(UTC);
        } catch (Exception e) {
            return;
        }
        format.setTimeZone(TimeZone.getDefault());
        String localTime = format.format(UtcDate.getTime());
        System.out.println(localTime);

    }




    @Test
    public void test2(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse("20200531");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(week);
        System.out.println(getUtcDate());
    }

    @Test
    public void test3() throws ParseException{
        // 0 周日 1 周一 -- 6 周六
        int config = 2; // 周二为第一天
        // 每周第7天运行
        int day = 7;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = format.parse("20200531");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("week is " + week + ";  day is " + day);
        boolean bln;
        if (week > config){
            System.out.println(" week > config;  week - config = " + (week - config));
            bln = week - config == day;
        }else {
            System.out.println("else; week + 1 + config = " + (week + 1 + config));
            bln = week + 1 + config == day;
        }
        System.out.println(bln);
    }

    /**
     * 获取当前UTC时间
     * @return
     */
    public static String getUtcDate(){
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format.format(now);
    }

}
