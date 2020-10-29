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
import com.gp.application.model.Formation;

/**
 * @author Ibrahima Ibnu Omar
 *
 */
@Service
public class FormationService {

public static final String col_name ="formation";
	
	public List<Formation> getAllFormation() throws InterruptedException,ExecutionException{
		Firestore dbFirestore = FirestoreClient.getFirestore();
		List<Formation> formations = new ArrayList<Formation>();
		//asynchronously retrieve all documents
		ApiFuture<QuerySnapshot> future = dbFirestore.collection(col_name).get();
		// future.get() blocks on response
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		for (QueryDocumentSnapshot document : documents) {
		  System.out.println(document.getId() + " => " + document.toObject(Formation.class));
		  formations.add(document.toObject(Formation.class));
		}
		
		return formations;
	}
	
	public String saveFormationDetails(Formation formation) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(col_name).document(formation.getId()).set(formation);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
	
	public Formation getFormationDetails(String nom) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(col_name).document(nom);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Formation formation = null;

        if(document.exists()) {
            formation = document.toObject(Formation.class);
            return formation;
        }else {
            return null;
        }
    }
	
	public String updateFormationDetails(Formation formation) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(col_name).document(formation.getId()).set(formation);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
	
	public String deleteFormation(String nom) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApi = dbFirestore.collection(col_name).document(nom).delete();
        return "Document with Formation ID "+nom+" has been deleted";
    }

}
