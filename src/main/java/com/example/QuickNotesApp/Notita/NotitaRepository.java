package com.example.QuickNotesApp.Notita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotitaRepository extends JpaRepository<Notita, Integer> {

}
