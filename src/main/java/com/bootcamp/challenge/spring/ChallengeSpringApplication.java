package com.bootcamp.challenge.spring;

import com.bootcamp.challenge.spring.enums.SortType;
import com.bootcamp.challenge.spring.utils.ShortStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ChallengeSpringApplication {

    public static void main(String[] args) throws IllegalAccessException {
        SpringApplication.run(ChallengeSpringApplication.class, args);
        SortType sortType = SortType.valueOf(1);
        ShortStrategy.valueOf(sortType.name()).sort(new ArrayList<>());
    }

}
