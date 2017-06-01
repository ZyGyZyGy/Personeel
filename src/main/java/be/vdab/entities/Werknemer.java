package be.vdab.entities; 

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.sun.istack.NotNull;

@Entity
@Table(name = "werknemers")
@NamedEntityGraph(name = "Werknemer.metOndergeschikten",
	attributeNodes = @NamedAttributeNode("ondergeschikten"))
public class Werknemer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank
    @Length(min = 1, max = 50)
    private String familienaam;
    
    @NotBlank
    @Length(min = 1, max = 50)
    private String voornaam;
    
    @Email
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chefid")
    private Werknemer chef;
    
    @OneToMany(mappedBy = "chef")
    private Set<Werknemer> ondergeschikten;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jobtitelid")
    private Jobtitel jobtitel;
    
    @NumberFormat(style = Style.NUMBER)
    @NotNull
    @Min(0)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal salaris;
    
    @Version
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
    
    public Set<Werknemer> getOndergeschikten() {
	return Collections.unmodifiableSet(ondergeschikten);
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
