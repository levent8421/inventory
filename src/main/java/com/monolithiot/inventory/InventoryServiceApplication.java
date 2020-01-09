package com.monolithiot.inventory;

import com.monolithiot.inventory.commons.context.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * Create By Levent8421
 * Create Time: 2020/1/8 11:45
 * Class Name: InventoryServiceApplication
 * Author: Levent8421
 * Description:
 * 系统启动入口
 *
 * @author Levent*421
 */
@SpringBootApplication(scanBasePackages = Constants.BASE_PACKAGE)
@MapperScan(basePackages = Constants.MyBatis.MAPPER_PACKAGES)
public class InventoryServiceApplication {
    /**
     * 主方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
}
