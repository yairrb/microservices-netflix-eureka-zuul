package com.formacionbdi.springboot.app.item.springboot.servicio.item.controllers;

import com.formacionbdi.springboot.app.item.springboot.servicio.item.interfaces.ItemService;
import com.formacionbdi.springboot.app.item.springboot.servicio.item.models.Item;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RefreshScope
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private Environment environment;

    @Value("${configuracion.texto}")
    private String texto;

    @GetMapping("/listar")
    public List<Item>listar() {
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public Item detalle (@PathVariable Long id, @PathVariable Integer cantidad) {
        return itemService.findById(id, cantidad);
    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfiguracion() {

        Map<String, String> json= new HashMap<>();
        json.put("texto", texto);

        if(environment.getActiveProfiles().length > 0 && environment.getActiveProfiles()[0].equals("dev")){
            json.put("Autor-nombre", environment.getProperty("configuracion.autor.nombre"));
        }


        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

    public Item metodoAlternativo(Long id, Integer cantidad){

        Item  itemAlternativo = new Item();
        Producto productoAlternativo = new Producto();
        productoAlternativo.setPrecio(700.0);
        productoAlternativo.setNombre("Producto default");
        productoAlternativo.setId(id);
        itemAlternativo.setCantidad(cantidad);
        itemAlternativo.setProducto(productoAlternativo);

        return itemAlternativo;
    }
}
