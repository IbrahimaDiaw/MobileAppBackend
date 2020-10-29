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
import com.gp.application.model.Absence;

/**
 * @author Ibrahima Ibnu Omar
 *
 */
@Service
public class AbsenceService {
	
public static final String col_name ="absence";
	
	public List<Absence> getAllAbsence() throws InterruptedException,ExecutionException{
		Firestore dbFirestore = FirestoreClient.getFirestore();
		List<Absence> absences = new ArrayList<Absence>();
		//asynchronously retrieve all documents
		ApiFuture<QuerySnapshot> future = dbFirestore.collection(col_name).get();
		// future.get() blocks on response
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		for (QueryDocumentSnapshot document : documents) {
		  System.out.println(document.getId() + " => " + document.toObject(Absence.class));
		  absences.add(document.toObject(Absence.class));
		}
		
		return absences;
	}
	
	public String saveAbsenceDetails(Absence absence) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(col_name).document(absence.getId()).set(absence);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
	
	public Absence getAbsenceDetails(String nom) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(col_name).document(nom);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Absence absence = null;

        if(document.exists()) {
            absence = document.toObject(Absence.class);
            return absence;
        }else {
            return null;
        }
    }
	
	public String updateAbsenceDetails(Absence absence) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(col_name).document(absence.getId()).set(absence);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
	
	public String deleteAbsence(String nom) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApi = dbFirestore.collection(col_name).document(nom).delete();
        return "Document with Absence ID "+nom+" has been deleted";
    }


}
