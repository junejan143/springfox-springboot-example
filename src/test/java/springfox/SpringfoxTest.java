package springfox;

import com.gemini.NecServerApplication;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileOutputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author 章源辰
 * @time: 2016/11/7 14:54
 * @describion:
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NecServerApplication.class, loader = SpringApplicationContextLoader.class)
public class SpringfoxTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void getSwaggerJson() throws Exception {
        //获取swagger扫描整个接口后产生的json文件
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs").accept("application/json;charset=utf-8"))
                .andExpect(status().isOk())
                .andReturn();


        //直接生成swagger.json文件
        /*  MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs").accept("application/json;charset=utf-8"))
                .andDo(SwaggerResultHandler.outputDirectory("src/test/java").build())
                .andExpect(status().isOk())
                .andReturn();*/

        MockHttpServletResponse response = mvcResult.getResponse();
        String swaggerJson = response.getContentAsString();
        swaggerJson = swaggerJson.replace("\"status\":200,\"message\":\"\",\"data\":", "");

        String filePath = "src/test/data.json";
        HttpClient httpClient = HttpClients.createDefault();
        File file = new File(filePath);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(swaggerJson.getBytes());
        fos.close();

        //将json文件发送给远程服务器
        String url = "http://127.0.0.1:3000/file_upload";
        HttpPost post = new HttpPost(url);

        FileBody fileBody = new FileBody(file);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addPart("file", fileBody);
        post.setEntity(multipartEntityBuilder.build());
        // 执行请求
        HttpResponse httpResponse = httpClient.execute(post);
        if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()) {
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(EntityUtils.toString(entity));
        } else {
            System.out.println(httpResponse.getStatusLine().getStatusCode() + "+++++error++++");
        }
        // 释放资源
        post.releaseConnection();
    }

}
