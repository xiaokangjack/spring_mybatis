package com.kang.mabatis.test;


import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.abel533.entity.Example;
import com.kang.mybatis.mapper.NewUserMapper;
import com.kang.mybatis.pojo.User;
public class NewUserMapperTest {

    private NewUserMapper newUserMapper;

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext*.xml");
        this.newUserMapper = applicationContext.getBean(NewUserMapper.class);
    }

    @Test
    public void testSelectOne() {
        User record = new User();
        // 设置查询条件
        record.setuserName("zhangsan");
        record.setPassword("123456");
        User user = this.newUserMapper.selectOne(record);
        System.out.println(user);
    }

    @Test
    public void testSelect() {
        User record = new User();
        // 设置查询条件
        record.setuserName("zhangsan");
        List<User> list = this.newUserMapper.select(record);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectCount() {
        System.out.println(this.newUserMapper.selectCount(null));
    }

    @Test
    public void testSelectByPrimaryKey() {
    	//注意selectByPrimaryKey的输入参数必须是Long型，不能是string类型。new Long(1)
        User user = this.newUserMapper.selectByPrimaryKey(new Long(1));
        System.out.println(user);
    } 
    
    @Test
    public void testInsert() {
        User record = new User();
        // 设置查询条件
        record.setuserName("test_username_3");
        //record.setAge(20);
        //record.setBirthday(new Date());
        record.setCreated(new Date());
        //record.setName("test_name_1");
        //record.setPassword("123456");
        record.setSex(1);
        record.setUpdated(new Date());
        //使用所有的字段作为插入语句的字段
        int count = this.newUserMapper.insert(record);
        System.out.println(count);
        System.out.println(record.getId());
    }

    @Test
    public void testInsertSelective() {
        User record = new User();
        // 设置查询条件
        record.setuserName("test_username_2");
        //record.setAge(20);
       // record.setBirthday(new Date());
        record.setCreated(new Date());
       // record.setName("test_name_1");
       // record.setPassword("123456");
        record.setSex(1);
        record.setUpdated(new Date());
        //将不为null的字段作为插入语句的字段
        int count = this.newUserMapper.insertSelective(record);
        System.out.println(count);
        System.out.println(record.getId());
    }

    @Test
    public void testDelete() {
//        this.newUserMapper.delete(null);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        System.out.println(this.newUserMapper.deleteByPrimaryKey(9L));
    }

    @Test
    public void testUpdateByPrimaryKey() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        User record = new User();
        record.setId(1L);
        record.setAge(24);
        this.newUserMapper.updateByPrimaryKeySelective(record);
    }

    @Test
    public void testSelectCountByExample() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteByExample() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectByExample() {
        Example example = new Example(User.class);
        List<Object> values = new ArrayList<Object>();
        values.add(1L);
        values.add(2L);
        values.add(3L);
        //批量查询
        //example.createCriteria().andIn("id", values);
        //单个查询
        example.createCriteria().andEqualTo("id", 1L);
        List<User> list = this.newUserMapper.selectByExample(example);
        
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdateByExampleSelective() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateByExample() {
        fail("Not yet implemented");
    }

}
