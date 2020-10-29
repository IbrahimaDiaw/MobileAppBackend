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

import com.gp.application.services.FormationService;
import com.gp.application.model.Formation;

/**
 * @author Ibrahima Ibnu Omar
 *
 */
@CrossOrigin("*")
@RequestMapping("/api/formation")
@RestController
public class FormationController {
	
	@Autowired
	private FormationService formationService;
	
	@GetMapping("/")
	public List<Formation> getAllFormation() throws InterruptedException, ExecutionException{
		return formationService.getAllFormation();
	}

	@PostMapping("/create")
	public String createNewFormation(@RequestBody Formation formation) throws
	          InterruptedException, ExecutionException {
		
		return formationService.saveFormationDetails(formation);
	}
	
	@GetMapping("/details/{id}")
	public Formation getFormatiomById(@PathVariable("id") String id) throws InterruptedException,
	              ExecutionException {
		return formationService.getFormationDetails(id);
	}
	
	@PutMapping("/update/{id}")
	public String updateFormationById(@PathVariable("id") String id, @RequestBody Formation formation)
	                 throws InterruptedException, ExecutionException {
		return formationService.updateFormationDetails(formation);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteFormationById(@PathVariable("id") String id) throws InterruptedException, ExecutionException {
		
		return formationService.deleteFormation(id);
	}
}
 