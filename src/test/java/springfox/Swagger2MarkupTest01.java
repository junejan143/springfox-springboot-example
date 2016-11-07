package springfox;


import com.gemini.NecServerApplication;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.swagger.config.SwaggerConfig;
import org.asciidoctor.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author 章源辰
 * @time: 2016/11/3 10:44
 * @describion:
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {NecServerApplication.class, SwaggerConfig.class})
public class Swagger2MarkupTest01 {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void convertSwaggerToAsciiDoc() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/v2/api-docs")
                .accept("application/json;charset=utf-8"))
                .andExpect(status().isOk())
                .andReturn();

        //文档输出目录
        String outputDirectory = "docs/restful/generated";
        Path outputDirectoryPath = Paths.get(outputDirectory);
        MockHttpServletResponse response = mvcResult.getResponse();
        String swaggerJson = response.getContentAsString();
        swaggerJson = swaggerJson.replace("{\"status\":200,\"message\":\"\",\"data\":", "");
        swaggerJson = swaggerJson.substring(0, swaggerJson.length());
        System.out.println(swaggerJson);
        Swagger2MarkupConverter.from(swaggerJson)
                .build()
                .toFolder(outputDirectoryPath);

        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        Attributes attributes = new Attributes();
        attributes.setCopyCss(true);
        attributes.setLinkCss(false);
        attributes.setSectNumLevels(3);
        attributes.setAnchors(true);
        attributes.setSectionNumbers(true);
        attributes.setHardbreaks(true);
        attributes.setTableOfContents(Placement.LEFT);
        attributes.setAttribute("generated", "generated");
        OptionsBuilder optionsBuilder = OptionsBuilder.options()
                .backend("html5")
                .docType("book")
                .eruby("")
                .inPlace(true)
                .safe(SafeMode.UNSAFE)
                .attributes(attributes);
        String asciiInputFile = "docs/restful/index.adoc";
        asciidoctor.convertFile(
                new File(asciiInputFile),
                optionsBuilder.get());

    }
}
