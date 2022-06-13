package com.example.student_schedule_app.Database;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.student_schedule_app.model.Activity;
import com.example.student_schedule_app.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ActivityFirestoreManager {
    private static ActivityFirestoreManager activitesFirestoreManager;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference activitiesCollectionReference;

    private ActivityFirestoreManager(){

        firebaseFirestore = FirebaseFirestore.getInstance();
        activitiesCollectionReference = firebaseFirestore.collection("Activity");
    }

    public static ActivityFirestoreManager newInstance() {
        if (activitesFirestoreManager == null){
            activitesFirestoreManager = new ActivityFirestoreManager();
        }
        return activitesFirestoreManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getActivities(Timestamp timestamp, OnCompleteListener listener){
        Date date = timestamp.toDate();
        LocalDate localDateStart = dateToLocal(date);
        LocalDate localDateEnd = localDateStart.plusDays(1);
        Timestamp timestamp_end = new Timestamp(localToDate(localDateEnd));
        Log.d("ACT_MAN", "timestamp_end: "+timestamp_end.toString());
        activitiesCollectionReference.whereGreaterThan("begin_time",timestamp).whereLessThanOrEqualTo("begin_time",timestamp_end).get().addOnCompleteListener(listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getCertainActivities(Timestamp timestamp, OnCompleteListener listener, List<String> courses){
        Date date = timestamp.toDate();
        LocalDate localDateStart = dateToLocal(date);
        LocalDate localDateEnd = localDateStart.plusDays(1);
        Timestamp timestamp_end = new Timestamp(localToDate(localDateEnd));
        Log.d("ACT_MAN", "timestamp_end: "+timestamp_end.toString());
        activitiesCollectionReference.whereGreaterThan("begin_time",timestamp).whereLessThanOrEqualTo("begin_time",timestamp_end).whereIn("courseName",courses).get().addOnCompleteListener(listener);
    }

    public void createActivity(Activity activity){

        activitiesCollectionReference.add(activity);
    }

    public void updateActivity(Activity activity){
        activitiesCollectionReference.document(activity.getActivityId()).set(activity);
    }

    public void deleteActivity(String activityId){
        activitiesCollectionReference.document(activityId).delete();
    }

    public void clearActivities(){
        activitiesCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()){
                    deleteActivity(doc.getId());
                }
            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate dateToLocal(Date date){
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Date localToDate(LocalDate localDate){
        return java.util.Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

}
