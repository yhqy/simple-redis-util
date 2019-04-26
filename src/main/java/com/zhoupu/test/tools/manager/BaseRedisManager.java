package com.zhoupu.test.tools.manager;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xchen
 * @date 2019-04-26
 **/
public abstract class BaseRedisManager implements IRedisManager {

    protected abstract Jedis getJedis();

    private String host;
    private int database;
    private int timeout = Protocol.DEFAULT_TIMEOUT;
    private String password;
    private JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

    @Override
    public byte[] get(byte[] key) {
        if (key == null) {
            return null;
        }
        byte[] value = null;
        Jedis jedis = getJedis();
        try {
            value = jedis.get(key);
        } finally {
            jedis.close();
        }
        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value, int expireTime, TimeUnit timeUnit) {
        if (key == null) {
            return null;
        }
        try (Jedis jedis = getJedis()) {
            jedis.set(key, value);
            if (expireTime > 0) {
                int expireTimeSeconds = (int) TimeUnit.SECONDS.convert(expireTime, timeUnit);
                jedis.expire(key, expireTimeSeconds);
            }
        }
        return value;
    }

    @Override
    public void del(byte[] key) {
        if (key == null) {
            return;
        }
        Jedis jedis = getJedis();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    //使用cursor方式读取，提高读取性能
    public Set<byte[]> keys(byte[] pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = getJedis();

        try {
            keys = new HashSet<byte[]>();
            ScanParams params = new ScanParams();
            int count = 100; //每次读100个
            params.count(count);
            params.match(pattern);
            byte[] cursor = ScanParams.SCAN_POINTER_START_BINARY;
            ScanResult<byte[]> scanResult;
            do {
                scanResult = jedis.scan(cursor, params);
                keys.addAll(scanResult.getResult());
                cursor = scanResult.getCursorAsBytes();
            } while (scanResult.getStringCursor().compareTo(ScanParams.SCAN_POINTER_START) > 0);
        } finally {
            jedis.close();
        }
        return keys;

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        if (null != timeout) {
            this.timeout = timeout;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }
}
