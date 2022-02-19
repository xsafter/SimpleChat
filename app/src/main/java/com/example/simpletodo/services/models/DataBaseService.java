package com.example.simpletodo.services.models;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataBaseService {
    public static Task<Void> setTestModel(String key, TestModel model) {
        return FirebaseDatabase.getInstance()
                .getReference(key)
                .setValue(model);
    }

    public static void getTestModel(String key, ValueEventListener eventListener) {
        FirebaseDatabase.getInstance()
                .getReference(key)
                .addListenerForSingleValueEvent(eventListener);
    }

    public static Task<Void> remove(String key) {
        return FirebaseDatabase
                .getInstance()
                .getReference()
                .removeValue();
    }
}