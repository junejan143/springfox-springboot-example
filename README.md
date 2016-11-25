# springfox-springboot-example
springfox集成springboot的demo

##第一种方式

```xml
 <dependency>
      <groupId>io.springfox</groupId>
     <artifactId>springfox-swagger2</artifactId>
     <version>${springfox.version}</version>
 </dependency>
 <dependency>
     <groupId>io.springfox</groupId>
     <artifactId>springfox-swagger-ui</artifactId>
     <version>${springfox.version}</version>
 </dependency>
```
>通过运行SpringfoxTest类中的测试用例可以将springfox生成的json文件存在项目中并且
使用Httpclient发送到远程的nodejs服务器对应的接口，在远程服务器上部署swagger UI界面，
就可以达到查看远程API的效果


##第二种方式

```xml
<plugin>
    <groupId>com.github.kongchen</groupId>
    <artifactId>swagger-maven-plugin</artifactId>
    <version>3.1.5-zychen-SNAPSHOT</version>
    <configuration>
        <apiSources>
            <apiSource>
                <type>SpringMVC-Extend</type>
                <locations>com.gemini.controller</locations>
                <!--<schemes>http,https</schemes>-->
                <host>127.0.0.1:8888</host>
                <!--<basePath>/api</basePath>-->
                <info>
                    <title>Swagger-Extend Maven Plugin Sample</title>
                    <version>v1</version>
                    <description>This is a sample for SpringMVC-Extend</description>
                    <contact>
                        <email>13338073698@163.com</email>
                        <name>zhangyuanchen</name>
                        <url>https://github.com/junejan143</url>
                    </contact>
                </info>
                <outputFormats>json</outputFormats>
                <swaggerDirectory>${project.build.directory}/generated/swagger-ui</swaggerDirectory>
                <swaggerFileName>demo</swaggerFileName>
            </apiSource>
        </apiSources>
    </configuration>
    <executions>
        <execution>
            <phase>compile</phase>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
</plugin>

```
>使用我改造另一个项目[swagger-maven-plugin](https://github.com/junejan143/swagger-maven-plugin)插件生成swagger.json文件
>扫描swagger的方式: 在pom文件添加该maven插件，然后直接运行 mvn clean compile



### 另外一个项目[swaggerUI-nodejs-example](https://github.com/junejan143/swaggerUI-nodejs-example)
>nodejs服务器，用来接收此项目的json文件
