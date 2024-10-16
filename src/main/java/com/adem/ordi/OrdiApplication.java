package com.adem.ordi;

import com.adem.ordi.entities.Marque;
import com.adem.ordi.entities.Pc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class OrdiApplication implements CommandLineRunner {
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(OrdiApplication.class, args);

    }
    @Override
    public void run(String... args) throws Exception {

        repositoryRestConfiguration.exposeIdsFor(Pc.class, Marque.class);
    }


}