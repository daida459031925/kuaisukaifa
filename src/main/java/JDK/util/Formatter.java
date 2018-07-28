package JDK.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;

public class Formatter {
    private java.util.Formatter formatter = new java.util.Formatter();

    private Logger log = LoggerFactory.getLogger(Formatter.class);
    @Test
    public void TestFormatter(){
        log.info(formatter.toString());
        StringBuffer sb = new StringBuffer("代打");
        File file = new File("C:\\core.dmp");
        try {
            formatter = new java.util.Formatter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        log.info(formatter.out().toString());
    }
}
