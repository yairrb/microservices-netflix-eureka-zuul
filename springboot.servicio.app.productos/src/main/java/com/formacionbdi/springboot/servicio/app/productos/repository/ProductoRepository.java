package com.formacionbdi.springboot.servicio.app.productos.repository;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {



}
