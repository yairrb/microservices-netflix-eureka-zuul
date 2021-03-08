package com.formacionbdi.springboot.servicio.app.productos.interfaces;


import com.formacionbdi.springboot.app.commons.models.entity.Producto;

import java.util.List;

public interface ProductoService {

    public List<Producto> findAll();

    public Producto findById(Long id);


    public  Producto save(Producto producto);

    public void deleteById(Long id);
}