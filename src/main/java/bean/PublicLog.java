package bean;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现LOG接口来进行日志打印
 */
public interface PublicLog {
    public static org.apache.log4j.Logger logger = Logger.getLogger(PublicLog.class);
    public static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PublicLog.class);
}
