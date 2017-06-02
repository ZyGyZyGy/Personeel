package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import be.vdab.entities.Werknemer;

public interface WerknemerService {

    void create(Werknemer werknemer);
    
    Optional<Werknemer> read(long id);
    
    void update(Werknemer werknemer);
    
    void delete(long id);
    
    List<Werknemer> findAll();
    
    Optional<Werknemer> findWerknemerMetHoogsteTitel();
    
    void geefOpslag(Werknemer werknemer, BigDecimal opslag);
    
    List<Werknemer> findByJobtitel(long id);
}
