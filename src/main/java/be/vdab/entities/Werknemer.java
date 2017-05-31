package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class Werknemer implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String voornaam;
    private String familienaam;
    private String email;
    
    private Werknemer chef;
    
    private Set<Werknemer> ondergeschikten;
    
    private Jobtitel jobtitel;
    
    private BigDecimal salaris;
    
    private long versie;

    public Werknemer() {

    }

    public Werknemer(String voornaam, String familienaam, String email, BigDecimal salaris) {
	this.voornaam = voornaam;
	this.familienaam = familienaam;
	this.email = email;
	this.salaris = salaris;
    }

    public Werknemer(long id, String voornaam, String familienaam, String email, BigDecimal salaris) {
	this(voornaam, familienaam, email, salaris);
	this.id = id;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getVoornaam() {
	return voornaam;
    }

    public void setVoornaam(String voornaam) {
	this.voornaam = voornaam;
    }

    public String getFamilienaam() {
	return familienaam;
    }

    public void setFamilienaam(String familienaam) {
	this.familienaam = familienaam;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Werknemer getChef() {
	return chef;
    }

    public void setChef(Werknemer chef) {
	this.chef = chef;
    }


    public Jobtitel getJobtitel() {
        return jobtitel;
    }

    public BigDecimal getSalaris() {
	return salaris;
    }

    public void setSalaris(BigDecimal salaris) {
	this.salaris = salaris;
    }
    
    

}
