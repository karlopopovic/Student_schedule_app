package com.example.student_schedule_app.data.model;

import com.google.firebase.firestore.DocumentId;

public class Contact {
    @DocumentId
    private String username;
    private String password;

    public Contact(){}

    public Contact(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public final class ContactFirestoreDbContact
    {
        public static final String COLLECTION_NAME = "contacts";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        private void ContactsFirestoreDbContract() {}
    }



}
