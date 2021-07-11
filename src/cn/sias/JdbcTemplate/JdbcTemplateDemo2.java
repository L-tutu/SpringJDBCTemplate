package cn.sias.JdbcTemplate;

import cn.sias.domain.Dept;
import cn.sias.utils.JDBCDruidUtils;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo2 {

    JdbcTemplate template = new JdbcTemplate(JDBCDruidUtils.getDataSource());


    //1. 修改1001号数据的salary为10000
    @Test
    public void test1(){
        String sql = "update emp set salary = 10000 where id = ?";
        template.update(sql,1001);

    }
    //2. 添加一条记录
    @Test
    public void test2(){
        String sql = "insert into emp(id,ename,salary) values(?,?,?)";
        template.update(sql,1111,"猪皮","11000");
    }


    //3. 删除刚才添加的记录
    @Test
    public void  test3(){
        String sql = "delete from emp where id = ?";
        template.update(sql,1111);
    }

    //4. 查询id为1001的记录，将其封装为Map集合
    @Test
    public void test4(){
        String sql ="select * from emp where id = ?";
        Map<String, Object> map = template.queryForMap(sql, 1001);
        System.out.println(map);
    }

    //5. 查询所有的记录,将其封装为List
    @Test
    public void test5(){
        String sql ="select * from emp";
        List<Map<String, Object>> mapList = template.queryForList(sql);
        System.out.println(mapList);
    }

    //6. 查询所有记录，将其封装为Emp对象的list集合
    @Test
    public void test6(){
        String sql = "select * from dept";
        List<Dept> query = template.query(sql, new BeanPropertyRowMapper<Dept>(Dept.class));
        for (Dept dept : query) {  //iter快捷键
            System.out.println(dept);
        }
    }

    //7.查询总记录数
    @Test
    public void test7(){
        String sql = "select COUNT(id) from emp";
        Long aLong = template.queryForObject(sql, Long.class);
        System.out.println(aLong);
    }


}
