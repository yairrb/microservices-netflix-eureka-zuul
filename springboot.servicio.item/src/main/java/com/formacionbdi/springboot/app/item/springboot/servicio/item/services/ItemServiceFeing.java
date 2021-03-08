package com.formacionbdi.springboot.app.item.springboot.servicio.item.services;

import com.formacionbdi.springboot.app.item.springboot.servicio.item.interfaces.ItemService;
import com.formacionbdi.springboot.app.item.springboot.servicio.item.interfaces.ProductoClienteRest;
import com.formacionbdi.springboot.app.item.springboot.servicio.item.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class ItemServiceFeing implements ItemService {

    @Autowired
    private ProductoClienteRest clientFeing;


    @Override
    public List<Item> findAll() {
        return clientFeing.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer catidad) {
        return new Item(clientFeing.detalle(id), catidad);
    }
}
