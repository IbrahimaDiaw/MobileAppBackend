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

import com.gp.application.services.CongesService;
import com.gp.application.model.Conges;

/**
 * @author Ibrahima Ibnu Omar
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("api/conges")
public class CongesController {
	
	@Autowired
	private CongesService congesService;
	
	@GetMapping("/")
	public List<Conges> getAllConges() throws InterruptedException, ExecutionException {
		
		return congesService.getAllConges();
		
	}
	
	@PostMapping("/create")
	public String createConges(@RequestBody Conges conges) throws InterruptedException, ExecutionException {
		
		return congesService.saveCongesDetails(conges);
	}

	@GetMapping("/details/{id}")
	public Conges getCongesById(@PathVariable("id") String id) throws InterruptedException, ExecutionException {
		
		return congesService.getCongesDetails(id);
	}
	
	@PutMapping("/update/{id}")
	public String updateById(@PathVariable("id") String id, @RequestBody Conges conges) throws
	               InterruptedException, ExecutionException {
		return congesService.updateCongesDetails(conges);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable("id") String id) throws InterruptedException, ExecutionException {
		
		return congesService.deleteConges(id);
	}
}
