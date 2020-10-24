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

import com.gp.application.model.Employee;
import com.gp.application.services.EmployeeService;

/**
 * @author Ibrahima Ibnu Omar
 *
*/
@CrossOrigin("*")
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public List<Employee> getAllEmployees() throws InterruptedException, ExecutionException {
		
		return employeeService.getAllEmployee();
	}
	
	@PostMapping("/create")
	public String saveEmployee(@RequestBody Employee employee) throws InterruptedException, ExecutionException {
		
		return employeeService.saveEmployeeDetails(employee);
	}
	
	@GetMapping("/details/{id}")
	public Employee getEmployeeById(@PathVariable("id") String id) throws InterruptedException, ExecutionException{
		
		return employeeService.getEmployeeDetails(id);
	}
	
	@PutMapping("/update/{id}")
	public String updateEmployeeById(@PathVariable("id") String id,@RequestBody Employee employee)
						throws InterruptedException, ExecutionException{
		return employeeService.updateEmployeeDetails(employee);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmployeeById(@PathVariable("id") String id) throws InterruptedException,
					ExecutionException {
		   employeeService.deleteEmployee(id);
		   return "Deleted Successfull";
		
	}

}
