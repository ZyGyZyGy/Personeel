package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "jobtitels")
public class Jobtitel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @NotBlank
    @Length(min = 1, max = 50)
    private String naam;

    @OneToMany(mappedBy = "jobtitel")
    private Set<Werknemer> werknemers;

    @Version
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

    public String getNaam() {
	return naam;
    }

    public void setNaam(String naam) {
	this.naam = naam;
    }

    public Set<Werknemer> getWerknemers() {
	return Collections.unmodifiableSet(werknemers);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((naam == null) ? 0 : naam.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Jobtitel))
	    return false;
	Jobtitel other = (Jobtitel) obj;
	if (naam == null) {
	    if (other.naam != null)
		return false;
	} else if (!naam.equals(other.naam))
	    return false;
	return true;
    }

}
