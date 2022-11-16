# 启动类

`com.alibaba.nacos.Nacos`

启动的时候需要添加VM参数`-Dnacos.standalone=true`

这个参数不知道是什么意思`-DembeddedStorage=true`

gRPC的启动类`com.alibaba.nacos.client.config.impl.ClientWorker.ClientWorker`

`com.alibaba.nacos.core.remote.grpc.GrpcRequestAcceptor.request` 入手方法

`com.alibaba.nacos.naming.remote.rpc.handler.InstanceRequestHandler` 这里执行注册或者接触注册的流程

`com.alibaba.nacos.naming.core.v2.index.ServiceStorage` 数据存储地方



`com.alibaba.nacos.naming.remote.rpc.handler.ServiceQueryRequestHandler#handle`在这里处理GRPC请求，然后把数据返回给

# 配置MySQL

`docker run --name nacos_mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=nacos -e MYSQL_USER=nacos -e MYSQL_PASSWORD=nacos -e MYSQL_DATABASE=nacos -d mysql:8.0.26`

`mysql-schema.sql`执行sql



`com.alibaba.nacos.config.server.controller.ConfigController`这里会接收请求把所有的配置保存下来