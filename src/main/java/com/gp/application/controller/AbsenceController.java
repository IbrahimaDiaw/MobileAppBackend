/**
 * 
 */
package com.gp.application.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gp.application.services.AbsenceService;
import com.gp.application.model.Absence;

/**
 * @author Ibrahima Ibnu Omar
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("api/absences")
public class AbsenceController {
	
	@Autowired
	private AbsenceService absenceService;
	
	@GetMapping("/")
	public List<Absence> getAllAbsences() throws InterruptedException, ExecutionException{
		return absenceService.getAllAbsence();
	}
	
	@PostMapping("/create")
	public String createNewAbsence(@RequestBody Absence absence) throws InterruptedException, ExecutionException {
		return absenceService.saveAbsenceDetails(absence);
	}

	@GetMapping("/details/{id}")
	public Absence getAbsenceById(@PathVariable("id") String id ) throws InterruptedException, ExecutionException {
		
		return absenceService.getAbsenceDetails(id);
	}
	
	@PutMapping("/update/{id}")
	public String updateAbsenceById(@PathVariable("id") String id, @RequestBody Absence absence) throws InterruptedException,
				ExecutionException {
		return absenceService.updateAbsenceDetails(absence);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteAbsenceById(@PathVariable("id") String id) throws InterruptedException, ExecutionException {
		
		return absenceService.deleteAbsence(id);
	}
}
