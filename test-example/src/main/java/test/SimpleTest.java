package test;

import org.junit.Test;

/**
 * Created by zzq on 2020/4/26.
 */
public class SimpleTest {
    @Test
    public void test(){
        String file = "/opt/vox/20200426/01/0000/HM10101202004260228080002.wav";
        String fileTwo = "opt/emp-cpp/";
        fileTwo = fileTwo + file.substring(1);
        System.out.println(fileTwo);
    }
}
