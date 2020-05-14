package com.sean;

import com.sean.hall.HallManager;
import com.sean.server.MessageCenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    HallManager hm;

    @Override
    public void run(String... args) throws Exception {
        hm.run();
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
