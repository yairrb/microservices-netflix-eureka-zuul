package com.formacionbdi.springboot.app.item.springboot.servicio.item.interfaces;


import com.formacionbdi.springboot.app.item.springboot.servicio.item.models.Item;

import java.util.List;

public interface ItemService {

    public List<Item> findAll();

    public Item findById(Long id, Integer catidad);
}
