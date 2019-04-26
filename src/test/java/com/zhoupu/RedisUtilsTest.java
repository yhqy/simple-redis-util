package com.zhoupu;

import com.zhoupu.commonsdb.dto.RentCloudWarehouse;
import com.zhoupu.test.tools.RedisUtils;
import org.junit.Test;

import java.util.Set;

/**
 * @author xchen
 * @date 2019-04-26
 **/
public class RedisUtilsTest {

    private String key = "testKey";

    @Test
    public void testSetAndGet() {
        //init, do not do it more than once !
        //if password is empty, use null replace!
        RedisUtils.init("localhost:6379", null, 6);
        RentCloudWarehouse rentCloudWarehouse = new RentCloudWarehouse();
        rentCloudWarehouse.setCompanyName("test");
        rentCloudWarehouse.setAddress("address xxxx");
        RedisUtils.set(key, rentCloudWarehouse);
        RentCloudWarehouse rentCloudWarehouse1 = RedisUtils.get(key, RentCloudWarehouse.class);
        System.out.println(rentCloudWarehouse1.getCompanyName() + " : " + rentCloudWarehouse1.getAddress());
    }

    @Test
    public void testDel() {
        //init, do not do it more than once !
        //if password is empty, use null replace!
        RedisUtils.init("localhost:6379", null, 6);
        RedisUtils.del(key);
    }

    @Test
    public void testKeys() {
        //init, do not do it more than once !
        //if password is empty, use null replace!
        RedisUtils.init("localhost:6379", null, 6);
        RedisUtils.set("a:1", new Integer(1));
        RedisUtils.set("a:2", new Integer(2));
        RedisUtils.set("a:3", new Integer(3));
        String pattern = "a:*";
        Set<String> keys = RedisUtils.keys(pattern);
        keys.forEach(System.out::println);
    }

    @Test
    public void testSentinel() {
        //init, do not do it more than once !
        //if password is empty, use null replace!
        RedisUtils.init("192.168.1.200:27380,192.168.1.200:27381,192.168.1.200:27382", "secret", 0, 10000, "redis7380");
        RentCloudWarehouse rentCloudWarehouse = new RentCloudWarehouse();
        rentCloudWarehouse.setCompanyName("test");
        rentCloudWarehouse.setAddress("address xxxx");
        RedisUtils.set(key, rentCloudWarehouse);
        RentCloudWarehouse rentCloudWarehouse1 = RedisUtils.get(key, RentCloudWarehouse.class);
        System.out.println(rentCloudWarehouse1.getCompanyName() + " : " + rentCloudWarehouse1.getAddress());
    }

}
