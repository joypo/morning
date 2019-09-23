# 1. 创建表
```sql
CREATE TABLE `WORKER_NODE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `HOST_NAME` varchar(64) DEFAULT NULL,
  `PORT` varchar(64) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `LAUNCH_DATE` date DEFAULT NULL,
  `MODIFIED` timestamp NULL DEFAULT NULL,
  `CREATED` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8
```

# 2.maven引用
```
<dependency>
    <groupId>com.example</groupId>
    <artifactId>id-generator-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

# 3.使用

UidGenerator接口提供了 UID 生成和解析的方法，提供了两种实现:
* DefaultUidGenerator 实时生成
* CachedUidGenerator 生成一次id之后，按序列号+1生成一批id，缓存，供之后请求

如对UID生成性能有要求, 请使用CachedUidGenerator
```java
//@Resource
//private UidGenerator defaultUidGenerator;

@Resource
private UidGenerator cachedUidGenerator;

@Test
public void testSerialGenerate() {
    // Generate UID
    long uid = cachedUidGenerator.getUID();

    // Parse UID into [Timestamp, WorkerId, Sequence]
    // {"UID":"450795408770","timestamp":"2019-02-20 14:55:39","workerId":"27","sequence":"2"}
    System.out.println(cachedUidGenerator.parseUID(uid));

}
```
# 4.可选设置
## 自定义配置

以下为可选配置, 如未指定将采用默认值
```yaml
uid:
  timeBits: 30             # 时间位, 默认:30
  workerBits: 16           # 机器位, 默认:16
  seqBits: 7               # 序列号, 默认:7
  epochStr: "2019-02-20"   # 初始时间, 默认:"2019-02-20"
  enableBackward: true    # 是否容忍时钟回拨, 默认:true
  maxBackwardSeconds: 1    # 时钟回拨最长容忍时间（秒）, 默认:1
  CachedUidGenerator:     # CachedUidGenerator相关参数
    boostPower: 3          # RingBuffer size扩容参数, 可提高UID生成的吞吐量, 默认:3
    paddingFactor: 50      # 指定何时向RingBuffer中填充UID, 取值为百分比(0, 100), 默认为50
    #scheduleInterval: 60    # 默认:不配置此项, 即不实用Schedule线程. 如需使用, 请指定Schedule线程时间间隔, 单位:秒
```
## 可选实现
选用CachedUidGenerator时，可以选择实现“拒绝策略”的拓展
* 拒绝策略: 当环已满, 无法继续填充时
  默认无需指定, 将丢弃Put操作, 仅日志记录. 如有特殊需求, 请实现RejectedPutBufferHandler接口(支持Lambda表达式
* 拒绝策略: 当环已空, 无法继续获取时
  默认无需指定, 将记录日志, 并抛出UidGenerateException异常. 如有特殊需求, 请实现RejectedTakeBufferHandler接口(支持Lambda表达式)