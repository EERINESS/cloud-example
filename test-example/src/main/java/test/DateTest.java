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
    private static final String TYPE_BEGIN = "begin";   // 标识处理录音开始时间
    private static final String TYPE_END = "end";   // 标识处理录音结束时间

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
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = format.parse("20200627032121");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //calendar.set(Calendar.DATE, 1);
        //calendar.roll(Calendar.DATE, -1);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(format.format(calendar.getTime()));
        System.out.println(week);
        String jobTime = "1,2,3,4,5";
        System.out.println(jobTime.indexOf("2"));
    }

    @Test
    public void test3(){
        int i = 5;
        System.out.println(i++);
        System.out.println(++i);

    }
    /**
     * 获取当前系统配置的一周中第一天的时间加几天的日期是周几
     * @param reduceNumber    需要添加的天数
     * @return
     */
    public static int getDateWeek(int beginWeek, int reduceNumber){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.DAY_OF_WEEK, getWeekLogo(beginWeek));
        System.out.println("当前周第一天日期为 ： " + format.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_WEEK, reduceNumber-1);
        System.out.println("当前周第一天加 ： " + reduceNumber + "天后日期为" + format.format(calendar.getTime()));
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    public static int getWeekLogo(int week){
        Calendar calendar = Calendar.getInstance();
        switch (week) {
            case 0: return calendar.SUNDAY;
            case 1: return calendar.MONDAY;
            case 2: return calendar.TUESDAY;
            case 3: return calendar.WEDNESDAY;
            case 4: return calendar.THURSDAY;
            case 5: return calendar.FRIDAY;
            case 6: return calendar.SATURDAY;
            default: return -1;
        }
    }



    @Test
    public void test4(){
        String beginTime = "20200627032121";
        String endTime = "20200627032921";
        String jobTime = "1,2,3,4,5";
        System.out.println("beginTime : " + getJobDifferDay(beginTime, jobTime, TYPE_BEGIN));
        System.out.println("endTime : " + getJobDifferDay(endTime, jobTime, TYPE_END));
    }




    /**
     *  获取如果开始结束时间处理重复呼入时间时，其中对时长差计算中包含不工作时间的处理
     * @param strDate   时间节点
     * @param jobTime   工作时间节点
     * @param type  类型，对开始时间处理还是结束时间
     * @return
     */
    private String getJobDifferDay(String strDate, String jobTime, String type){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String strWeek = getStrWeek(calendar.get(Calendar.DAY_OF_WEEK));
        int dayDiffer = dayDiffer(strWeek, jobTime, 0, type);
        return getReduceDate(strDate, dayDiffer, 3);
    }

    /**
     * 重复呼入录音开始时间对应工作时间处理
     * @param dateWeek  对应时间所属周几
     * @param jobTime   工作时间节点
     * @param count 如果时间节点不属于工作时间需要加减多少天算工作时间
     * @param type  类型，对开始时间处理还是结束时间
     * @return
     */
    private int dayDiffer(String dateWeek, String jobTime, int count, String type){
        if (jobTime.indexOf(dateWeek) == -1){
            if (TYPE_BEGIN.equals(type)){
                if ("7".equals(dateWeek)){
                    dateWeek = "1";
                }else {
                    dateWeek = String.valueOf(Integer.valueOf(dateWeek) + 1);
                }
                count ++;
            }else if (TYPE_END.equals(type)){
                if ("1".equals(dateWeek)){
                    dateWeek = "7";
                }else {
                    dateWeek = String.valueOf(Integer.valueOf(dateWeek) - 1);
                }
                count --;
            }
            count = dayDiffer(dateWeek, jobTime, count, type);
        }
        return count;
    }

    /**
     * java 日期方法中与对应印象中的周几表示不一致的处理
     * @param week
     * @return
     */
    private String getStrWeek(int week){
        String strWeek= "";
        switch (week) {
            case 1:
                strWeek = "7";
                break;
            case 2:
                strWeek = "1";
                break;
            case 3:
                strWeek = "2";
                break;
            case 4:
                strWeek = "3";
                break;
            case 5:
                strWeek = "4";
                break;
            case 6:
                strWeek = "5";
                break;
            case 7:
                strWeek = "6";
                break;
            default:
                break;
        }
        return strWeek;
    }


    /**
     * 给定时间加或者减去多少小时或多少个月
     * @param reduceNumber    加或者减去的小时数或月数或日数
     * @param type  1 小时    2 月   3 天
     * @return
     */
    public static String getReduceDate(String time, Integer reduceNumber, Integer type){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        if (type == 1){
            ca.add(Calendar.HOUR, reduceNumber);
        }else if (type == 2){
            ca.add(Calendar.MONTH, reduceNumber);
        }else if (type == 3){
            ca.add(Calendar.DAY_OF_MONTH, reduceNumber);
        }

        return sdf.format(ca.getTime());
    }

}
