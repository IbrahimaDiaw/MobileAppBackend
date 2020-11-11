/**
 * 
 */
package com.gp.application.model;

import java.security.SecureRandom;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ibrahima Ibnu Omar
 *
 */
public class Absence {
	private String id = this.generateString("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 12);
	private String date;
	private float duree;
	private String motif;
	@JsonProperty
	private List<String> employee;
	
	

	
	/**
	 * @param id
	 * @param date
	 * @param duree
	 * @param motif
	 * @param employee
	 */
	public Absence(String id, String date, float duree, String motif,  @JsonProperty("employee") List<String> employee) {
		super();
		this.id = id;
		this.date = date;
		this.duree = duree;
		this.motif = motif;
		this.employee = employee;
	}



	/**
	 * 
	 */
	public Absence() {
		super();
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}


	/**
	 * @return the duree
	 */
	public float getDuree() {
		return duree;
	}


	/**
	 * @param duree the duree to set
	 */
	public void setDuree(float duree) {
		this.duree = duree;
	}


	/**
	 * @return the motif
	 */
	public String getMotif() {
		return motif;
	}


	/**
	 * @param motif the motif to set
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	} 


	/**
	 * @return the employee
	 */
	public List<String> getEmployee() {
		return employee;
	}


	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(List<String> employee) {
		this.employee = employee;
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
