/**
 * 
 */
package com.gp.application.model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * @author Ibrahima Ibnu Omar
 *
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties (value = {"handler"})
public class Employee {
	
	private String id = this.generateString("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 12);
	private String prenom;
	private String nom;
	private String adresse;
	private String email;
	private String fonction;
	private BigInteger tel;
	private float salaire;
	@JsonProperty
	private List<Departement> departement ;
	
	
	/**
	 * 
	 */
	public Employee() {
		super();
	}

	/**
	 * @param id
	 * @param prenom
	 * @param nom
	 * @param adresse
	 * @param email
	 * @param fonction
	 * @param tel
	 * @param salaire
	 * @param departement
	 */
	public Employee(String id, String prenom, String nom, String adresse, String email, String fonction, BigInteger tel,
			float salaire, List<Departement> departement) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.fonction = fonction;
		this.tel = tel;
		this.salaire = salaire;
		this.departement = departement;
	}
	
	

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fonction
	 */
	public String getFonction() {
		return fonction;
	}

	/**
	 * @param fonction the fonction to set
	 */
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	/**
	 * @return the tel
	 */
	public BigInteger getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(BigInteger tel) {
		this.tel = tel;
	}

	/**
	 * @return the salaire
	 */
	public float getSalaire() {
		return salaire;
	}

	/**
	 * @param salaire the salaire to set
	 */
	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}

	/**
	 * @return the departement
	 */
	public List<Departement> getDepartement() {
		return departement;
	}

	/**
	 * @param departement the departement to set
	 */
	public void setDepartement(List<Departement> departement) {
		this.departement = departement;
	}

	//fonction generatrice de cle primaire
	public String generateString(String alphabet, int length) {
        return generateString(alphabet, length, new SecureRandom()::nextInt);
    }

    // nextInt = bound -> n in [0, bound)
    public String generateString(String source, int length, IntFunction<Integer> nextInt) {
        StringBuilder sb = new StringBuilder();
        IntStream.generate(source::length)
                .boxed()
                .limit(length)
                .map(nextInt::apply)
                .map(source::charAt)
                .forEach(sb::append);

        return sb.toString();
    }

}
