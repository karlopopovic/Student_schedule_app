package com.example.student_schedule_app.Database;

import com.example.student_schedule_app.model.Course;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class CourseFirestoreManager {
    private static CourseFirestoreManager coursesFirestoreManager;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference coursesCollectionReference;

    private CourseFirestoreManager(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        coursesCollectionReference = firebaseFirestore.collection("Course");
    }

    public static CourseFirestoreManager newInstance() {
        if (coursesFirestoreManager == null){
            coursesFirestoreManager = new CourseFirestoreManager();
        }
        return coursesFirestoreManager;
    }

    public void deleteCourse(String courseId){
        coursesCollectionReference.document(courseId).delete();
    }

    public void clearCourses(){
        coursesCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()){
                    deleteCourse(doc.getId());
                }
            }
        });
    }

    public void createCourse(Course course){
        coursesCollectionReference.add(course);
    }

    public void getAllCourses(OnCompleteListener<QuerySnapshot> onCompleteListener){
        coursesCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

}
