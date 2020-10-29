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
import com.gp.application.model.Conges;

/**
 * @author Ibrahima Ibnu Omar
 *
 */
@Service
public class CongesService {

public static final String col_name ="conges";
	
	public List<Conges> getAllConges() throws InterruptedException,ExecutionException{
		Firestore dbFirestore = FirestoreClient.getFirestore();
		List<Conges> conges = new ArrayList<Conges>();
		//asynchronously retrieve all documents
		ApiFuture<QuerySnapshot> future = dbFirestore.collection(col_name).get();
		// future.get() blocks on response
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		for (QueryDocumentSnapshot document : documents) {
		  System.out.println(document.getId() + " => " + document.toObject(Conges.class));
		  conges.add(document.toObject(Conges.class));
		}
		
		return conges;
	}
	
	public String saveCongesDetails(Conges conges) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(col_name).document(conges.getId()).set(conges);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
	
	public Conges getCongesDetails(String nom) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(col_name).document(nom);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Conges conges = null;

        if(document.exists()) {
            conges = document.toObject(Conges.class);
            return conges;
        }else {
            return null;
        }
    }
	
	public String updateCongesDetails(Conges conges) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(col_name).document(conges.getId()).set(conges);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
	
	public String deleteConges(String nom) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApi = dbFirestore.collection(col_name).document(nom).delete();
        return "Document with Conges ID "+nom+" has been deleted";
    }

}
