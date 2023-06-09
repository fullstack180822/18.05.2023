package spring1.web1.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPooled;
import spring1.web1.demo.services.RedisDetailsConfig;

public class CacheRepositoryImpl implements ICacheRepository {

    @Autowired
    JedisPooled jedisPool;

    @Autowired
    private RedisDetailsConfig redisDetailsConfig;

    public void createCacheEntity(String key, String value) {
        jedisPool.setex(key, redisDetailsConfig.getTtl(), value);
    }

    public String getCacheEntity(String key, String value) {
        if (isKeyExist(key)){
            return jedisPool.get(key);
        }
        return null;
    }

    public void updateCacheEntity(String key, String value) {
        if (isKeyExist(key)){
            jedisPool.setex(key, redisDetailsConfig.getTtl(), value);
        }
    }

    public Boolean isKeyExist(String key) {
        return jedisPool.exists(key);
    }

    public void removeKey(String key) {
        if (isKeyExist(key)) {
            jedisPool.del(key);
        }
    }

}
