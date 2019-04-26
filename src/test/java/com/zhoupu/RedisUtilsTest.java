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

    private String key = "erp:cloudwarehouse:v1:123321";

    @Test
    public void testSetAndGet() {
        // 注意： RedisUtils 只可以初始化一次，重复初始化会报异常，所以应当在系统启动时初始化
        RedisUtils.init("localhost:6379", null, 6);
        RentCloudWarehouse rentCloudWarehouse = new RentCloudWarehouse();
        rentCloudWarehouse.setCompanyName("test");
        rentCloudWarehouse.setAddress("江苏省南京市雨花客厅");
        RedisUtils.set(key, rentCloudWarehouse);
        RentCloudWarehouse rentCloudWarehouse1 = RedisUtils.get(key, RentCloudWarehouse.class);
        System.out.println(rentCloudWarehouse1.getCompanyName() + " : " + rentCloudWarehouse1.getAddress());
    }

    @Test
    public void testDel() {
        // 注意： RedisUtils 只可以初始化一次，重复初始化会报异常，所以应当在系统启动时初始化
        RedisUtils.init("localhost:6379", null, 6);
        RedisUtils.del(key);
    }

    @Test
    public void testKeys() {
        // 注意： RedisUtils 只可以初始化一次，重复初始化会报异常，所以应当在系统启动时初始化
        RedisUtils.init("localhost:6379", null, 6);
        RedisUtils.set("a:1", new Integer(1));
        RedisUtils.set("a:2", new Integer(2));
        RedisUtils.set("a:3", new Integer(3));
        String pattern = "a:*";
        Set<String> keys = RedisUtils.keys(pattern);
        keys.forEach(System.out::println);
    }

    @Test
    public void testSentinel(){
        RedisUtils.init("192.168.1.200:27380,192.168.1.200:27381,192.168.1.200:27382","secret", 0, 10000, "redis7380");
        RentCloudWarehouse rentCloudWarehouse = new RentCloudWarehouse();
        rentCloudWarehouse.setCompanyName("test");
        rentCloudWarehouse.setAddress("江苏省南京市雨花客厅");
        RedisUtils.set(key, rentCloudWarehouse);
        RentCloudWarehouse rentCloudWarehouse1 = RedisUtils.get(key, RentCloudWarehouse.class);
        System.out.println(rentCloudWarehouse1.getCompanyName() + " : " + rentCloudWarehouse1.getAddress());
    }

}
