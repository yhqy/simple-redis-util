# simple-redis-util

simple redis util, support singleton, sentinel server

## how to config/init ?

```
//init, do not do it more than once !
//if password is empty, use null replace!

//singleton
RedisUtils.init("localhost:6379", password, 6);

//sentinel
RedisUtils.init("192.168.1.200:27380,192.168.1.200:27381,192.168.1.200:27382","secret", 0, 10000, "redis7380");
```

## how to use ?

```
//store value, value can be any object
RedisUtils.set(key,value);

//query value, auto convert to tclass
T t = RedisUtils.get(key, tclass);

//delete key
RedisUtils.del(key);

//query keys by keyPattern
Set<String> keys = RedisUtils.keys(keyPattern);
```

## use case 
```
see RedisUtilsTest.java
```
