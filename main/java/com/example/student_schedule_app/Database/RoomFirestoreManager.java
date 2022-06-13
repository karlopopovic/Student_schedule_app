package com.example.student_schedule_app.Database;

import com.example.student_schedule_app.model.Room;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class RoomFirestoreManager {
    private static RoomFirestoreManager RoomFirestoreManager;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference roomCollectionReference;

    private RoomFirestoreManager(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        roomCollectionReference = firebaseFirestore.collection("Room");
    }

    public static RoomFirestoreManager newInstance() {
        if (RoomFirestoreManager == null){
            RoomFirestoreManager = new RoomFirestoreManager();
        }
        return RoomFirestoreManager;
    }


    public void createRoom(Room room){
        roomCollectionReference.add(room);
    }

    public void getAllRooms(OnCompleteListener<QuerySnapshot> onCompleteListener){
        roomCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }


    public void deleteRoom(String roomId){
        roomCollectionReference.document(roomId).delete();
    }

    public void clearRooms(){
        roomCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()){
                    deleteRoom(doc.getId());
                }
            }
        });
    }

}
