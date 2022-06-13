package com.example.student_schedule_app.Database;

import com.example.student_schedule_app.model.Collage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class CollageFirestoreManager {

    private static CollageFirestoreManager collageFirestoreManager;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collageCollectionReference;

    private CollageFirestoreManager(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        collageCollectionReference = firebaseFirestore.collection("Collage");
    }

    public static CollageFirestoreManager newInstance() {
        if (collageFirestoreManager == null){
            collageFirestoreManager = new CollageFirestoreManager();
        }
        return collageFirestoreManager;
    }

    public void createCollage(Collage Collage){
        collageCollectionReference.add(Collage);
    }

    public void getAllCollages(OnCompleteListener<QuerySnapshot> onCompleteListener){
        collageCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

    public void deleteCollage(String collageId){
        collageCollectionReference.document(collageId).delete();
    }

    public void clearCollages(){
        collageCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()){
                    deleteCollage(doc.getId());
                }
            }
        });
    }




}
