package com.example.student_schedule_app.Database;

import com.example.student_schedule_app.model.Degree;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class DegreeFirestoreManager {
    private static DegreeFirestoreManager degreesFirestoreManager;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference degreesCollectionReference;

    private DegreeFirestoreManager(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        degreesCollectionReference = firebaseFirestore.collection("Degree");
    }

    public static DegreeFirestoreManager newInstance() {
        if (degreesFirestoreManager == null){
            degreesFirestoreManager = new DegreeFirestoreManager();
        }
        return degreesFirestoreManager;
    }


    public void createDegree(Degree degree){
        degreesCollectionReference.add(degree);
    }

    public void getAllDegrees(OnCompleteListener<QuerySnapshot> onCompleteListener){
        degreesCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

    public void deleteDegree(String degreeId){
        degreesCollectionReference.document(degreeId).delete();
    }

    public void clearDegrees(){
        degreesCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()){
                    deleteDegree(doc.getId());
                }
            }
        });
    }

}
