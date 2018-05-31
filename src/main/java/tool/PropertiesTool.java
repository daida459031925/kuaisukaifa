package tool;

import java.io.IOException;
import java.util.Properties;

public class PropertiesTool {

    private static Properties p =null;

    static {
        p = new Properties();
        try {
            /**
             * 加载配置文件
             */
            p.load(PropertiesTool.class.getClassLoader().getResourceAsStream("changliang.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("加载db.properties失败", e);
        }
    }

    /**
     * 这里填写的为配置文件中的key
     * @param Key
     * @return
     */
    public static String getProperties(String Key){
        return p.getProperty(Key);
    }

}
