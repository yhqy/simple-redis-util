package com.zhoupu.test.tools.serializer;

import com.zhoupu.test.tools.exception.SerializationException;

import java.io.*;

/**
 * @author xchen
 * @date 2019-04-26
 **/
public class StringRedisSerializer implements RedisSerializer<String> {

    private static final String DEFAULT_CHARSET = "UTF-8";

    @Override
    public byte[] serialize(String str) throws SerializationException {
        try {
            return (str == null ? null : str.getBytes(DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("serialize error, string=" + str, e);
        }
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        try {
            return (bytes == null ? null : new String(bytes, DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("deserialize error", e);
        }
    }
}
