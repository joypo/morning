#### 备注

* 1、netty实现websocket
*  2、需要做健康检查，剔除下线的channel
*  3、耗时操作异步处理
*  4、集群利用redis的pub/sub实现