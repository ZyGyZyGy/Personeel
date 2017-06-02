package be.vdab.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import be.vdab.entities.Werknemer;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long>{

    @EntityGraph("Werknemer.metOndergeschikten")
    Werknemer findByChefIsNull();
    
    List<Werknemer> findByJobtitel(@Param("id") long id);
    
}
