package me.ijusthaveto.exam;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "me.ijusthaveto.exam.mapper")
@SpringBootApplication
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
        System.out.println("后台启动成功，Sa-Token配置如下：" + SaManager.getConfig());
    }

}
