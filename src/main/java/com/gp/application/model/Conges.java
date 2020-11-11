/**
 * 
 */
package com.gp.application.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ibrahima Ibnu Omar
 *
 */
public class Conges {

	private String id = this.generateString("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 12);
	private String dateDebut;
	private String dateFin;
	private String nature;
	@JsonProperty
	private ArrayList<String> employee;

	/**
	 * @param id
	 * @param dateFin
	 * @param dateDebut
	 * @param nature
	 * @param employee
	 */
	public Conges(String id, String dateDebut,String dateFin, String nature, @JsonProperty("employee") ArrayList<String> employee) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nature = nature;
        this.employee = employee;
	}

	/**
	 * 
	 */
	public Conges() {
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
	 * @return the dateDebut
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public String getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}
    

	/**
	 * @return the employee
	 */
	public ArrayList<String> getEmployee() {
		return employee;
		
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(ArrayList<String> employee) {
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
