# springfox-springboot-example
springfox集成springboot的demo

>通过运行SpringfoxTest类中的测试用例可以将springfox生成的json文件存在项目中并且
使用Httpclient发送到远程的nodejs服务器对应的接口，在远程服务器上部署swagger UI界面，
就可以达到查看远程API的效果

### swagger-maven-plugin插件
>扫描swagger的方式：mvn clean compile

### 另外一个项目[swaggerUI-nodejs-example](https://github.com/junejan143/swaggerUI-nodejs-example)
>nodejs服务器，用来接收此项目的json文件


