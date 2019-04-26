package com.zhoupu.test.tools.manager;

import com.sun.deploy.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xchen
 * @date 2019-04-26
 **/
public class RedisSentinelManager extends BaseRedisManager implements IRedisManager {

	private String masterName = "mymaster";
	private JedisSentinelPool jedisPool;

	@Override
	protected Jedis getJedis() {
		if (jedisPool == null) {
			init();
		}
		return jedisPool.getResource();
	}

	private void init() {
		synchronized (this) {
			if (jedisPool == null) {
				String[] sentinelHosts = getHost().split(",\\s*");
				Set<String> sentinels = new HashSet<String>();
				Collections.addAll(sentinels, sentinelHosts);
				jedisPool = new JedisSentinelPool(masterName, sentinels, getJedisPoolConfig(), getTimeout(), getTimeout(), getPassword(), getDatabase());
			}
		}
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		if(null != masterName && StringUtils.trimWhitespace(masterName).length() > 0){
			this.masterName = masterName;
		}
	}


}
