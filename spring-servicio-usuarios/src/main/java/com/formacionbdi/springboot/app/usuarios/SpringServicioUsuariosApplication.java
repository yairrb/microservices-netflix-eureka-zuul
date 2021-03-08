package com.formacionbdi.springboot.app.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.formacionbdi.springboot.app.commons.usuarios.models.entity"})
@SpringBootApplication
public class SpringServicioUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringServicioUsuariosApplication.class, args);
    }

}


