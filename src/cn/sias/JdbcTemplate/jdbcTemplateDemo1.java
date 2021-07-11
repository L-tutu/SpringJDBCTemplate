package cn.sias.JdbcTemplate;

import cn.sias.utils.JDBCDruidUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class jdbcTemplateDemo1 {
    public static void main(String[] args) {
        //1.导入jar包
        //2.创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCDruidUtils.getDataSource());
        //3.调用方法
       String sql = "update dept set loc = '杭州' where id= ?";
       template.update(sql,50);

    }


}
