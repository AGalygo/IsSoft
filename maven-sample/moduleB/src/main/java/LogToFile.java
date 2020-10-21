import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogToFile {

    private static final Logger logger = LoggerFactory.getLogger(
            LogToFile.class);

    private static final String FILENAME = "/file/does/not/exist";

    public static void main(String[] args) {

        logger.info("Just a log message.");
        logger.debug("Message for debug level.");
        int a = -1;
        if (a < 0) {
            logger.debug("a<0");
        }


    }
}
