package com.example.student_schedule_app.Database;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class UserFirestoreManager {

    private static UserFirestoreManager userFirestoreManager;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference userCollectionReference;

    private UserFirestoreManager(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        userCollectionReference = firebaseFirestore.collection("user");
    }

    public static UserFirestoreManager newInstance(){
        if (userFirestoreManager == null){
            userFirestoreManager = new UserFirestoreManager();
        }
        return userFirestoreManager;
    }

    public void getUser(String userId, OnCompleteListener<DocumentSnapshot> listener){
        userCollectionReference.document(userId).get().addOnCompleteListener(listener);
    }

}
