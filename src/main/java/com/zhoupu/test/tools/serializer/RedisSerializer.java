package com.zhoupu.test.tools.serializer;

import com.zhoupu.test.tools.exception.SerializationException;

/**
 * @author xchen
 * @date 2019-04-26
 **/
public interface RedisSerializer<T> {

    byte[] serialize(T t) throws SerializationException;

    T deserialize(byte[] bytes) throws SerializationException;
}
