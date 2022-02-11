package com.zhidao.kstb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 跨省通办户口迁移 项目启动类
 *
 * @author <致道科技>YunTong
 * @version 2.5.3
 * <p>Created on 2021-8-2<p>
 * <p>Updated on 2021-8-31<p>
 */
@SpringBootApplication
@EnableTransactionManagement
public class KstbApplication {
    // Simple Logging Facade for Java
    private final static Logger logger = LoggerFactory.getLogger(KstbApplication.class);
    public static void main(String[] args) {
        logger.info("======跨省通办服务端正在加载======");
        SpringApplication.run(KstbApplication.class, args);
        logger.info("======跨省通办服务端加载完毕======");
    }

}
