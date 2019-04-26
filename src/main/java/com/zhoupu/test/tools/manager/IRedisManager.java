package com.zhoupu.test.tools.manager;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xchen
 * @date 2019-04-26
 **/
public interface IRedisManager {

    byte[] get(byte[] key);

    byte[] set(byte[] key, byte[] value, int expire, TimeUnit timeUnit);

    void del(byte[] key);

    Set<byte[]> keys(byte[] pattern);

}
