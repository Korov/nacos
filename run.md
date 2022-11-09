# 启动类

`com.alibaba.nacos.Nacos`

启动的时候需要添加VM参数`-Dnacos.standalone=true`

这个参数不知道是什么意思`-DembeddedStorage=true`

gRPC的启动类`com.alibaba.nacos.client.config.impl.ClientWorker.ClientWorker`

`com.alibaba.nacos.core.remote.grpc.GrpcRequestAcceptor.request` 入手方法

`com.alibaba.nacos.naming.remote.rpc.handler.InstanceRequestHandler` 这里执行注册或者接触注册的流程

`com.alibaba.nacos.naming.core.v2.index.ServiceStorage` 数据存储地方