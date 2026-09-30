package com.turbotienda.TurboTienda;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface Repositorio extends JpaRepository<Modelo, Long> {
    List<Modelo> findByCategoria(String categoria);
    List<Modelo> findByNombre(String nombre);
}