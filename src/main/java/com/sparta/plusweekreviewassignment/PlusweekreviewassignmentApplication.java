package com.sparta.plusweekreviewassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication  //(exclude = SecurityAutoConfiguration.class) //Spring Security 인증 기능 제외
@EnableJpaAuditing  // timestamp 사용하기 위해 사용
public class PlusweekreviewassignmentApplication {

  public static void main(String[] args) {
    SpringApplication.run(PlusweekreviewassignmentApplication.class, args);
  }

}
