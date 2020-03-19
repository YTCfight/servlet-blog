package frank.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import frank.exception.SystemException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private static volatile DataSource DATA_SOURCE;

    private static final String URL = "jdbc:mysql://localhost:3306/blogdemo?characterEncoding=utf8&amp;useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DBUtil() {
    }

    private static DataSource getDataSource() {
        if (DATA_SOURCE == null) {
            synchronized (DBUtil.class) {
                if (DATA_SOURCE == null) {
                    DATA_SOURCE = new MysqlDataSource();
                    ((MysqlDataSource)DATA_SOURCE).setURL(URL);
                    ((MysqlDataSource)DATA_SOURCE).setUser(USERNAME);
                    ((MysqlDataSource)DATA_SOURCE).setPassword(PASSWORD);
                }
            }
        }
        return DATA_SOURCE;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new SystemException(Constant.DB_ERROR_CODE, "获取数据库连接失败", e);
        }
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw new SystemException(Constant.DB_ERROR_CODE, "释放数据库资源失败", e);
        }
    }

    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
