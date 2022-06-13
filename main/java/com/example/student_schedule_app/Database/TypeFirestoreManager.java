package com.example.student_schedule_app.Database;

import com.example.student_schedule_app.model.Type;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class TypeFirestoreManager {
    private static TypeFirestoreManager TypesFirestoreManager;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference typesCollectionReference;

    private TypeFirestoreManager(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        typesCollectionReference = firebaseFirestore.collection("Types");
    }

    public static TypeFirestoreManager newInstance() {
        if (TypesFirestoreManager == null){
            TypesFirestoreManager = new TypeFirestoreManager();
        }
        return TypesFirestoreManager;
    }


    public void createType(Type Type){
        typesCollectionReference.add(Type);
    }

    public void getAllTypes(OnCompleteListener<QuerySnapshot> onCompleteListener){
        typesCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }


    public void deleteType(String typeId){
        typesCollectionReference.document(typeId).delete();
    }

    public void clearTypes(){
        typesCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()){
                    deleteType(doc.getId());
                }
            }
        });
    }


}
