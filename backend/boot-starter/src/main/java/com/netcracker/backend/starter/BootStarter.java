package com.netcracker.backend.starter;

import com.netcracker.backend.configuration.DaoBeans;
import com.netcracker.backend.configuration.HibernateConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created by ilkh0715 on 30.10.2016.
 */
@SpringBootApplication
@Import({HibernateConfiguration.class, DaoBeans.class})
@ComponentScan(basePackages = "com.netcracker.backend")
public class BootStarter {

    public static void main(String[] args) {
        SpringApplication.run(BootStarter.class, args);
    }

}
