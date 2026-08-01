package com.pe.Ferreteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pe.Ferreteria.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
