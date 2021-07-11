package cn.sias.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid连接池的工具类
 */
public class JDBCDruidUtils {

    private static DataSource ds;

    static {
         try {
             //1.加载配置文件
             Properties pro = new Properties();
             InputStream is = JDBCDruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
             pro.load(is);
             //2.获取DataSource
             ds = DruidDataSourceFactory.createDataSource(pro);
         } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
             e.printStackTrace();
         }

    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws  SQLException{

        return  ds.getConnection();
    }

    /***
     * 关闭资源
     * @param stmt
     * @param conn
     */
    public static void close(Statement stmt,Connection conn){
       close(null,stmt,conn);
    }

    /**
     * 关闭资源
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 提供连接池
     * @return
     */
    public static DataSource getDataSource(){
        return  ds;
    }

}
