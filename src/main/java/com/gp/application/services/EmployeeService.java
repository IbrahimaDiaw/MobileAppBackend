/**
 * 
 */
package com.gp.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.gp.application.model.Employee;

/**
 * @author Ibrahima Ibnu Omar
 *
 */
@Service
public class EmployeeService {
	
	public static final String col_name ="employee";
	
	public List<Employee> getAllEmployee() throws InterruptedException,ExecutionException{
		Firestore dbFirestore = FirestoreClient.getFirestore();
		List<Employee> employees = new ArrayList<Employee>();
		//asynchronously retrieve all documents
		ApiFuture<QuerySnapshot> future = dbFirestore.collection(col_name).get();
		// future.get() blocks on response
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		for (QueryDocumentSnapshot document : documents) {
		  System.out.println(document.getId() + " => " + document.toObject(Employee.class));
		  employees.add(document.toObject(Employee.class));
		}
		
		return employees;
	}
	
	public String saveEmployeeDetails(Employee employee) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(col_name).document(employee.getId()).set(employee);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
	
	public Employee getEmployeeDetails(String nom) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(col_name).document(nom);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Employee Employee = null;

        if(document.exists()) {
            Employee = document.toObject(Employee.class);
            return Employee;
        }else {
            return null;
        }
    }
	
	public String updateEmployeeDetails(Employee employee) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(col_name).document(employee.getId()).set(employee);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
	
	public String deleteEmployee(String nom) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApi = dbFirestore.collection(col_name).document(nom).delete();
        return "Document with Employee ID "+nom+" has been deleted";
    }

}
