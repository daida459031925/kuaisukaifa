package JDK.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Formatter {
    private java.util.Formatter formatter = new java.util.Formatter();

    private Logger log = LoggerFactory.getLogger(Formatter.class);
    @Test
    public void TestFormatter(){
        log.info(formatter.toString());
    }
}
