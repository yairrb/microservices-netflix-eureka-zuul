package com.formacionbdi.springboot.app.item.springboot.servicio.item.interfaces;


import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name ="servicio-productos")
public interface ProductoClienteRest {


    @GetMapping("/listar")
    public List<Producto> listar();

    @GetMapping("/listar/{id}")
    public Producto detalle(@PathVariable("id") Long id);

}
