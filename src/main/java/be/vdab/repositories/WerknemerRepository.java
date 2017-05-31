package be.vdab.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Werknemer;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long>{

    @EntityGraph("Werknemer.metOndergeschikten")
    Werknemer findByChefIsNull();
    
}
