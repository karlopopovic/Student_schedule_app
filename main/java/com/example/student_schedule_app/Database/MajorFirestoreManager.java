package com.example.student_schedule_app.Database;

import com.example.student_schedule_app.model.Major;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MajorFirestoreManager {
    private static MajorFirestoreManager majorFirestoreManager;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference majorCollectionReference;

    private MajorFirestoreManager(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        majorCollectionReference = firebaseFirestore.collection("Majors");
    }

    public static MajorFirestoreManager newInstance() {
        if (majorFirestoreManager == null){
            majorFirestoreManager = new MajorFirestoreManager();
        }
        return majorFirestoreManager;
    }


    public void createMajor(Major major){
        majorCollectionReference.add(major);
    }

    public void getAllMajors(OnCompleteListener<QuerySnapshot> onCompleteListener){
        majorCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }


    public void deleteMajor(String majorId){
        majorCollectionReference.document(majorId).delete();
    }

    public void clearMajors(){
        majorCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()){
                    deleteMajor(doc.getId());
                }
            }
        });
    }


}
