package tool;
import bean.PublicLog;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.security.MessageDigest;
/**
 * MD5加密工具
 *
 */
public class MD5 implements Serializable, PublicLog{
    //private static Logger logger = Logger.getLogger(MD5.class);//打印log日志
    private static final long serialVersionUID = 1L;
    /**
     * Created by yangyibo on 17/2/7.
     */
    private static final String SALT = "583299e871fe3e6db8d2f98285a721ad";//防止被破解添加字符串进行处理
    /**
     * 为微信所有id和密钥进行加密的盐
     */
    private static final String salt="刀剣の神域";
        public static String encode(String password) {
            password = salt + password + SALT;
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            char[] charArray = password.toCharArray();
            byte[] byteArray = new byte[charArray.length];

            for (int i = 0; i < charArray.length; i++)
                byteArray[i] = (byte) charArray[i];
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }

                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        }

        public static void main(String[] args) {
            System.out.println(MD5.encode("abel"));
            logger.error(MD5.encode("abel"));
        }

}
