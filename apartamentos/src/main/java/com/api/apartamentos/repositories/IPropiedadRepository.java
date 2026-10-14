package com.api.apartamentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import com.api.apartamentos.models.PropiedadModel;
import java.util.List;
import java.util.Optional;

public interface IPropiedadRepository extends JpaRepository<PropiedadModel, Long> {
    @Override
    @EntityGraph(attributePaths = {"propietario"})
    List<PropiedadModel> findAll();

    @Override
    @EntityGraph(attributePaths = {"propietario"})
    Optional<PropiedadModel> findById(Long id);

    @EntityGraph(attributePaths = {"propietario"})
    List<PropiedadModel> findByTipo(String tipo);
    
    @EntityGraph(attributePaths = {"propietario"})
    List<PropiedadModel> findByPropietarioId(Long id);
}