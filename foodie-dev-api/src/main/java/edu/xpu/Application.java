package edu.xpu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描通用Mapper位置
@MapperScan(basePackages = {"edu.xpu.hcp.mapper"})
//扫描所有组件包
@ComponentScan(basePackages = {"edu.xpu","org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
