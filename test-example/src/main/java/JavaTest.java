import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzq on 2020/4/16.
 */
public class JavaTest {
    @Test
    public void test(){
        Map<String, String> map = new HashMap();
        map.put("我","我的内容");
        map.put("你","你的内容");
        map.remove("她");
        System.out.println(map.get("我"));
        System.out.println(map.get("你"));

    }

}
