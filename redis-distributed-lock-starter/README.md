# 开发一个spring-boot-starter的步骤
1. 新建一个maven项目
2. 需要一个配置类RedisAutoConfiguration，在配置类里面装配好需要提供出去的类
3. 使用@Enable配合@Import导入需要装配的类  或者  META-INF/spring.factories中配置
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.snowalker.demo.redis.RedisAutoConfiguration

## redis分布式锁starter--基于Redisson
### 配置文件
        redisson.lock.server.address=127.0.0.1:6379
        redisson.lock.server.type=standalone
### 更新记录
1. 改变配置方式，增加对不同Redis连接方式的支持
<br/>去除以下方法RedissonManager(String redisIp, String redisPort)

            public RedissonManager (String redisIp, String redisPort) {
                try {
                    String redisAddr = new StringBuilder("redis://")
                            .append(redisIp).append(":").append(redisPort)
                            .toString();
                    config.useSingleServer().setAddress(redisAddr);
                    redisson = (Redisson) Redisson.create(config);
                    LOGGER.info("初始化Redisson结束,redisAddress:" + redisAddr);
                } catch (Exception e) {
                    LOGGER.error("Redisson init error", e);
                    e.printStackTrace();
                }
            }



### redisson单机配置
```yaml
#redisson.lock.server.address=127.0.0.1:6379
#redisson.lock.server.type=standalone
#redisson.lock.server.password=
#redisson.lock.server.database=1
```
### redisson哨兵配置
**redisson.lock.server.address** 格式为: sentinel.conf配置里的sentinel别名,<br/>
sentinel1节点的服务IP和端口，sentinel2节点的服务IP和端口，sentinel3节点的服务IP和端口<br/>
比如sentinel.conf里配置为sentinel monitor my-sentinel-name 127.0.0.1 6379 2,那么这里就配置my-sentinel-name
```yaml
#redisson.server.address=my-sentinel-name,127.0.0.1:26379,127.0.0.1:26389,127.0.0.1:26399
#redisson.server.type=sentinel
#redisson.lock.server.password=
#redisson.lock.server.database=1
```
### 集群方式
redisson分布式锁配置--集群方式<br/>
cluster方式至少6个节点(3主3从，3主做sharding，3从用来保证主宕机后可以高可用)<br/>
地址格式为: 127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381,127.0.0.1:6382,127.0.0.1:6383,127.0.0.1:6384
```yaml
#redisson.server.address=127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381,127.0.0.1:6382,127.0.0.1:6383,127.0.0.1:6384
#redisson.server.type=cluster
#redisson.lock.server.password=
```

redisson分布式锁配置--主从<br/>
地址格式为**主节点,子节点,子节点**<br/>
代表主节点:127.0.0.1:6379，从节点127.0.0.1:6380，127.0.0.1:6381

```yaml
#redisson.server.address=127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
#redisson.server.type=masterslave
#redisson.lock.server.password=
#redisson.lock.server.database=1
```



