package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import be.vdab.entities.Werknemer;
import be.vdab.repositories.WerknemerRepository;

@Service
public class DefaultWerknemerService implements WerknemerService {

    private final WerknemerRepository werknemerRepository;

    public DefaultWerknemerService(WerknemerRepository werknemerRepository) {
	this.werknemerRepository = werknemerRepository;
    }

    @Override
    public void create(Werknemer werknemer) {
	werknemerRepository.save(werknemer);
    }
    
    @Override
    public Optional<Werknemer> read(long id) {
	return Optional.ofNullable(
		werknemerRepository.findOne(id));
    }
    
    @Override
    public void update(Werknemer werknemer) {
	werknemerRepository.save(werknemer);
    }
    
    @Override
    public void delete(long id) {
	Optional<Werknemer> optionalWerknemer
		= Optional.ofNullable(werknemerRepository.findOne(id));
	if (optionalWerknemer.isPresent()) {
	    werknemerRepository.delete(id);
	}
    }

    @Override
    public List<Werknemer> findAll() {
	return werknemerRepository.findAll();
    }

    @Override
    public Optional<Werknemer> findWerknemerMetHoogsteTitel() {
	return Optional.ofNullable(
		werknemerRepository.findByChefIsNull());
    }
    
    @Override
    public void geefOpslag(long id, BigDecimal opslag) {
	Optional<Werknemer> optionalWerknemer
		= Optional.ofNullable(werknemerRepository.findOne(id));
	if (optionalWerknemer.isPresent()) {
	    Werknemer werknemer = optionalWerknemer.get();
	    BigDecimal huidigeSalaris = werknemer.getSalaris();
	    werknemer.setSalaris(huidigeSalaris.add(opslag));
	    werknemerRepository.save(werknemer);
	}
    }
    
}









