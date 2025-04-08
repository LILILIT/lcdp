package org.example;

import org.example.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@RestController
public class P3Main {
    public static void main(String[] args) {
        SpringApplication.run(P3Main.class,args);
    }
    @Value("${server.port}")
    String port;
    @Value("${aa.bb}")
    String aabb;
    @GetMapping("/HealthCheck")
    public String healthCheck(){
        System.err.println(aabb+port);
        return aabb+port;
    }
    @Autowired
    TestMapper testMapper;
    @PostMapping("/testSql")
    public Object testSql(String sqlId){
        Object object = null;
        if("selectAll".equals(sqlId)){
            object=testMapper.selectAll();
        } else if ("selectOne".equals(sqlId)) {
            object=testMapper.selectOne();
        }
        System.err.println("test  revert");
        return object;
    }
}