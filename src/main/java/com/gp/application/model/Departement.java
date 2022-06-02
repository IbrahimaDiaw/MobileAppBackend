/**
 * 
 */
package com.gp.application.model;

import java.security.SecureRandom;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @author Ibrahima Ibnu Omar
 *
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties (value = {"handler"})
public class Departement {

	

    
	private String id = this.generateString("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 12);
	
	private String nom;
	private String  description;
	
	

	/**
	 * 
	 */
	public Departement() {
		super();
	}
	
	/**
	 * @param nom
	 * @param id
	 */
	public Departement(String nom ) {
		super();
		this.nom = nom;
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
	

	public String getDescription() {
		return description;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
