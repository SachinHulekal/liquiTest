package liqui.liqui;

/**
 * Hello world!
 *
 */

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Properties;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.FileSystemResourceAccessor;

// Handler value: example.HandlerInteger
public class App implements RequestHandler<Map<String, String>, Void> {
    @Override
    public Void handleRequest(Map<String, String> event, Context context) {
        try {
            LambdaLogger logger = context.getLogger();
            logger.log("EVENT TYPE: " + event.getClass());
            logger.log("Sachin it is wokring");
            Class.forName("com.mysql.jdbc.Driver");
            Properties props = new Properties();
            InputStream fis = App.class.getClassLoader().getResourceAsStream("db.properties");
            props.load(fis);
            fis.close();

            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            System.out.println(url);
            System.out.println(password);
            System.out.println(username);
            Connection con = DriverManager.getConnection(url, username, password);
            logger.log("con");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
