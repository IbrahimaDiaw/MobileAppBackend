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
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.WriteResult;
import com.gp.application.model.Departement;

/**
 * @author Ibrahima Ibnu Omar
 *
 */
@Service
public class DepartementService {
	
	public static final String COL_NAME="departement";
	
	public List<Departement> getDepartements() throws InterruptedException,ExecutionException{
		Firestore dbFirestore = FirestoreClient.getFirestore();
		List<Departement> departements = new ArrayList<Departement>();
		//asynchronously retrieve all documents
		ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
		// future.get() blocks on response
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		for (QueryDocumentSnapshot document : documents) {
		  System.out.println(document.getId() + " => " + document.toObject(Departement.class));
		  departements.add(document.toObject(Departement.class));
		}
		
		return departements;
	}

	
	public String saveDepartementDetails(Departement departement) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(departement.getId()).set(departement);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
	
	public Departement getDepartementDetails(String nom) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(nom);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Departement Departement = null;

        if(document.exists()) {
            Departement = document.toObject(Departement.class);
            return Departement;
        }else {
            return null;
        }
    }
	
	public String updateDepartementDetails(Departement departement) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(departement.getId()).set(departement);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
	
	public String deleteDepartement(String nom) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApi = dbFirestore.collection(COL_NAME).document(nom).delete();
        return "Document with Departement ID "+nom+" has been deleted";
    }
}
