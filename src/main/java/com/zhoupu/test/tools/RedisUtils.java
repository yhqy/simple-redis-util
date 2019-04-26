package com.zhoupu.test.tools;

import com.zhoupu.test.tools.exception.RedisInitException;
import com.zhoupu.test.tools.manager.BaseRedisManager;
import com.zhoupu.test.tools.manager.IRedisManager;
import com.zhoupu.test.tools.manager.RedisSentinelManager;
import com.zhoupu.test.tools.manager.RedisSingletonManager;
import com.zhoupu.test.tools.serializer.GenericJackson2JsonRedisSerializer;
import com.zhoupu.test.tools.serializer.RedisSerializer;
import com.zhoupu.test.tools.serializer.StringRedisSerializer;
import redis.clients.jedis.Protocol;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xchen
 * @date 2019-04-26
 **/
@SuppressWarnings("unchecked")
public class RedisUtils {

    private static IRedisManager redisManager;
    private static RedisSerializer<String> redisKeySerializer = new StringRedisSerializer();
    private static GenericJackson2JsonRedisSerializer redisValueSerializer = new GenericJackson2JsonRedisSerializer();

    public static void set(String key, Object value) {
        set(key, value, -1);
    }

    public static void set(String key, Object value, int expireSeconds) {
        byte[] keyBytes = redisKeySerializer.serialize(key);
        byte[] valueBytes = redisValueSerializer.serialize(value);
        getRedisManager().set(keyBytes, valueBytes, expireSeconds, TimeUnit.SECONDS);
    }

    public static <T> T get(String key, Class<T> requireType) {
        byte[] keyBytes = redisKeySerializer.serialize(key);
        byte[] valueBytes = getRedisManager().get(keyBytes);
        return redisValueSerializer.deserialize(valueBytes, requireType);
    }

    public static void del(String key) {
        byte[] keyBytes = redisKeySerializer.serialize(key);
        getRedisManager().del(keyBytes);
    }

    public static Set<String> keys(String pattern) {
        byte[] patternBytes = redisKeySerializer.serialize(pattern);
        Set<byte[]> keys = getRedisManager().keys(patternBytes);
        Set<String> result = new HashSet<>();
        for (byte[] bytes : keys) {
            result.add(redisKeySerializer.deserialize(bytes));
        }
        return result;
    }


    private static IRedisManager getRedisManager() {
        if (null == redisManager) {
            throw new RedisInitException("redis not init, please use RedisUtils.init(..) ");
        }
        return redisManager;
    }

    public static void init(String host, String password, int database) {
        init(host, password, database, Protocol.DEFAULT_TIMEOUT);
    }

    public static void init(String host, String password, int database, Integer timeout) {
        init(host, password, database, timeout, null);
    }


    public static void init(String host, String password, int database, Integer timeout, String masterName) {
        if (null != redisManager) {
            throw new RedisInitException("redis has inited, do not repeat !");
        }
        BaseRedisManager baseRedisManager;
        if (host.contains(",")) {
            baseRedisManager = new RedisSentinelManager();
            ((RedisSentinelManager) baseRedisManager).setMasterName(masterName);
        } else {
            baseRedisManager = new RedisSingletonManager();
        }
        baseRedisManager.setHost(host);
        baseRedisManager.setPassword(password);
        baseRedisManager.setDatabase(database);
        baseRedisManager.setTimeout(timeout);
        redisManager = baseRedisManager;
    }
}
