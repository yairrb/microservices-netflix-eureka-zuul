package com.formacionbdi.springboot.app.item.springboot.servicio.item.services;

import com.formacionbdi.springboot.app.item.springboot.servicio.item.interfaces.ItemService;
import com.formacionbdi.springboot.app.item.springboot.servicio.item.models.Item;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemServiceImp implements ItemService {


    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
        return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer catidad) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());


        Producto producto = clienteRest.getForObject("http://localhost:8001/listar/{id}", Producto.class, pathVariables);
        return new Item(producto, catidad);
    }
}
