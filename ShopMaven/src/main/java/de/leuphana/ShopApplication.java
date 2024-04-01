package de.leuphana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableJms
public class ShopApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
