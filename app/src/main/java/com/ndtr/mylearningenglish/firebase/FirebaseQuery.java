package com.ndtr.mylearningenglish.firebase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ndtr.mylearningenglish.activities.LoginActivity;
import com.ndtr.mylearningenglish.models.Topic;
import com.ndtr.mylearningenglish.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirebaseQuery<T> {

    public static final String dbLink = "https://my-english-2ad42-default-rtdb.firebaseio.com/";
    public static String TAG = "FireBase";
    public static String USERNAME = "";

    public static final String USERS = "users";
    public static final String TOPICS = "topic";
    public static final String WORDS = "words";

    public static User user;
    public static Topic topic;



    public static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(dbLink);

    public static void checkExistUsername(String username, ValueEventListener valueEventListener){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference(USERS).child(username);

        users.addListenerForSingleValueEvent(valueEventListener);
    }

    public static List<String> getAllTopicName(){
        List<String> topicsName = new ArrayList<>();
        DatabaseReference topics = firebaseDatabase.getReference(TOPICS);
        topics.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshotChild: dataSnapshot.getChildren()){
                    topicsName.add(dataSnapshotChild.getKey().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return  topicsName;
    }

    public static void getTopicByName(String topicName, ValueEventListener valueEventListener){

       DatabaseReference topics = firebaseDatabase.getReference(TOPICS).child(topicName);

       topics.addListenerForSingleValueEvent(valueEventListener);


    }

    public static void getAllTopicName(ValueEventListener valueEventListener){
        DatabaseReference topics = firebaseDatabase.getReference(TOPICS);
        topics.addValueEventListener(valueEventListener);
    }

}
