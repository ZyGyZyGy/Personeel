package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import be.vdab.entities.Werknemer;
import be.vdab.repositories.WerknemerRepository;

@ReadOnlyTransactionalService
public class DefaultWerknemerService implements WerknemerService {

    private final WerknemerRepository werknemerRepository;

    public DefaultWerknemerService(WerknemerRepository werknemerRepository) {
	this.werknemerRepository = werknemerRepository;
    }

    @Override
    @ModifyingTransactionalServiceMethod
    public void create(Werknemer werknemer) {
	werknemerRepository.save(werknemer);
    }
    
    @Override
    public Optional<Werknemer> read(long id) {
	return Optional.ofNullable(
		werknemerRepository.findOne(id));
    }
    
    @Override
    @ModifyingTransactionalServiceMethod
    public void update(Werknemer werknemer) {
	werknemerRepository.save(werknemer);
    }
    
    @Override
    @ModifyingTransactionalServiceMethod
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
    @ModifyingTransactionalServiceMethod
    public void geefOpslag(Werknemer werknemer, BigDecimal opslag) {
	BigDecimal huidigeSalaris = werknemer.getSalaris();
	werknemer.setSalaris(huidigeSalaris.add(opslag));
	werknemerRepository.save(werknemer);
    }

    @Override
    public List<Werknemer> findByJobtitel(long id) {
	return werknemerRepository.findByJobtitel(id);
    }
    
}









