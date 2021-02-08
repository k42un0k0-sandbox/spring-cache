package com.example.demo;

import com.example.demo.db.AuthorityDao;
import com.example.demo.db.AuthorityDto;
import com.example.demo.db.CustomerDao;
import com.example.demo.db.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class DemoApplication {
    Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    @Autowired
    CustomerDao customerDao;
    @Autowired
    AuthorityDao authorityDao;
    @Autowired
    PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner bootstrap() {
        return (args) -> {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setUsername("hello");
            customerDto.setPassword(encoder.encode("world"));
            customerDao.save(customerDto);
            logger.info("create a customer");

            AuthorityDto authorityDto = new AuthorityDto();
            authorityDto.setUsername("hello");
            authorityDto.setAuthority("ROLE_USER");
            authorityDao.save(authorityDto);
            logger.info("create a authority");
        };
    }

    @GetMapping("")
    public String index() {
        return "index";
    }
}
