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

import com.gp.application.model.Departement;
import com.gp.application.services.DepartementService;


/**
 * @author Ibrahima Ibnu Omar
 *
 */
@CrossOrigin(origins = "*")
@RequestMapping("/api/departements")
@RestController
public class DepartementController {

	@Autowired
	private DepartementService departementService;
	
	@GetMapping(value="/")
	public List<Departement> getAllDepartement() throws InterruptedException,ExecutionException{
		return departementService.getDepartements();
	}

	
	@GetMapping("/details/{id}")
	public Departement getDepartement(@PathVariable("id") String id)throws 
				InterruptedException, ExecutionException{
        return departementService.getDepartementDetails(id);
		
	}
	
	@PostMapping("/create")
    public String createDepartement(@RequestBody Departement departement ) throws InterruptedException, ExecutionException {
        return departementService.saveDepartementDetails(departement);
    }
	
	@PutMapping("/update/{id}")
    public String updateDepartement(@PathVariable("id") String id,@RequestBody Departement departement  ) throws InterruptedException, ExecutionException {
        return departementService.updateDepartementDetails(departement);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDepartement(@PathVariable("id") String id){
          departementService.deleteDepartement(id);
          return "Deleted Successfull";
    }
	
	
	
	
	
}
