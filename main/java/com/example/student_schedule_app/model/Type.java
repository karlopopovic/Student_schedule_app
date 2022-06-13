package com.example.student_schedule_app.model;

import com.google.firebase.firestore.DocumentId;

public class Type {
    @DocumentId
    private String typeId;
    private String typeName;

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public Type() {
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
