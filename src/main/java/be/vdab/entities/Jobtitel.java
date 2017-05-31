package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class Jobtitel implements Serializable {

    private static final long serialVersionUID = 1L;

    private long Id;
    private String naam;
    private Set<Werknemer> werknemers;
    
    private long versie;

    public Jobtitel() {
	
    }
    
    public Jobtitel(long id, String naam) {
	Id = id;
	this.naam = naam;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Set<Werknemer> getWerknemers() {
        return Collections.unmodifiableSet(werknemers);
    }



}
