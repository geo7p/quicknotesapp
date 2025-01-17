package com.example.QuickNotesApp.Eticheta;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtichetaRepository extends JpaRepository<Eticheta, Integer> {
    Optional<Eticheta> findByDenumire(String denumire);
}
