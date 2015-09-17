# Hello - Anonymous Chatting Website(匿名在线聊天交友网站)
This project has been removed to <https://github.com/wanghongfei/chat> using new framework(`Netty`) and new implementation.

### Architecture(架构)
* Updated to JDK8(更新至JDK8)
* hello-common: Classes used by all modules
* hello-service: Service layer implemented by **SpringRMI**, Spring4 and JPA2
* hello-web: Web layer implemented by **WebSocket** and SpringMVC
* This project is designed to be distributed(这一个分布式应用)

### Build(构建)
* Execute `build.sh` for build only, execute `build.sh test` for test only.(执行`build.sh`构建工程，执行`build.sh test`运行测试。更多选项请执行`build.sh help`查看)


### Tested Browsers(通过测试的浏览器)
* Chrome-39.0.2171.95 for Linux
* Firefox-34.0 for Linux
