package test;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zzq on 2020/4/26.
 */
public class SimpleTest {

    public static List<String> splitByRegexToStrCententList(String str, String regex) {
        Pattern p=Pattern.compile(regex);
        Matcher m=p.matcher(str);
        List<String> resultStrList = new ArrayList<>();
        while(m.find()){
            String key = m.group(1);
            resultStrList.add(key);
        }
        return resultStrList;
    }

    public static String replaceAll(String str,String replacement,String ... arguments) {
        for (String argument : arguments) {
            str = str.replaceAll(argument,replacement);
        }
        return str;
    }

    @Test
    public void test(){
        String str = "<\u001BM26P102261011090100ML007\u001B>-->{0};<\u001BM26P102261011090100ML004\u001B>-->{1};<\u001BM26P102261011090100ML006\u001B>-->{2};";
        String strValue = "<keyBDN000211115001key>\u001B172.16.10.97\u001BWIN-66GDGM5D1GC";

        List<String> keyList = splitByRegexToStrCententList(str, "<\u001B(.*?)\u001B>");
        for (String s : keyList) {
            str = str.replace(s,s + "测试");
        }
        String format = MessageFormat.format(str, strValue.split("\u001B"));

        List<String> keyList2 = splitByRegexToStrCententList(format, "<key(.*?)key>");
        for (String s : keyList2) {
            format = format.replace(s, s+"Key编码");
        }
        format = replaceAll(format,"","<\u001B","\u001B>","<key","key>");
        System.out.println(format);
    }
}
