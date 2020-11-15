package edu.xpu.hcp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**                                                                                ____________________
      _                _                                                           < 神兽护体，永无bug! >
    | |__  _   _  ___| |__   ___ _ __   __ _ _ __   ___ _ __   __ _                --------------------
   | '_ \| | | |/ __| '_ \ / _ \ '_ \ / _` | '_ \ / _ \ '_ \ / _` |                       \   ^__^
  | | | | |_| | (__| | | |  __/ | | | (_| | |_) |  __/ | | | (_| |                        \  (oo)\_______
 |_| |_|\__,_|\___|_| |_|\___|_| |_|\__, | .__/ \___|_| |_|\__, |                           (__)\       )\/\
                                   |___/|_|                |___/                                ||----w |
                                                                                                ||     ||
 * @author: huchengpeng
 * @date: 2020/11/15 15:58
 * @description: Swagger2相关配置
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * 原访问地址：
     *          http://localhost:port/swagger-ui.html
     * 美化后：
     *          http://localhost:port/doc.html
     */

    //配置Swagger2核心配置 docket
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                //用于定义API文档汇总信息;
                .apiInfo(apiInfo())
                //指定扫描controller
                .select().apis(RequestHandlerSelectors.basePackage("edu.xpu.hcp.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("天天吃货API文档")
                //联系人信息
                .contact(new Contact("CatWing",
                        "https://www.chengpengper.cn",
                        "2843053453@qq.com"))
                .description("专为天天吃货提供的API文档")
                .version("1.0.1")
                .termsOfServiceUrl("https://www.chengpengper.cn")
                .build();
    }

}
