package com.ndtr.mylearningenglish.firebase;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ndtr.mylearningenglish.activities.FillBlankExerciseActivity;
import com.ndtr.mylearningenglish.activities.LoginActivity;
import com.ndtr.mylearningenglish.models.Exercise;
import com.ndtr.mylearningenglish.models.Topic;
import com.ndtr.mylearningenglish.models.User;
import com.ndtr.mylearningenglish.models.Word;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirebaseAuth<T> {

    public static final String dbLink = "https://my-english-2ad42-default-rtdb.firebaseio.com/";
    public static final String storageLink = "gs://my-english-2ad42.appspot.com/";
    public static String TAG = "FireBase";
    public static String USERNAME = "";

    public static final String USERS = "users";
    public static final String TOPICS = "topic";
    public static final String WORDS = "words";
    public static final String WORDLIST = "wordList";
    public static final String EXERCISE = "exercises";

    public static User user;
    public static Topic topic;
    public static Word word;
    public static Exercise exercise;



    public static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(dbLink);
    public static FirebaseStorage firebaseStorage = FirebaseStorage.getInstance(storageLink);

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

    public static void getAllWordsFromList(List<String> wordsName, ValueEventListener valueEventListener){
        for (String eachWord: wordsName){
            DatabaseReference word = firebaseDatabase.getReference(WORDS).child(eachWord);
            word.addValueEventListener(valueEventListener);
        }

    }

    public static void getWordsFromID(String wordsID, ValueEventListener valueEventListener){
        DatabaseReference word = firebaseDatabase.getReference(WORDS).child(wordsID);
        word.addValueEventListener(valueEventListener);


    }

    public static void addWordToNoteBook(List<String> words){
        DatabaseReference wordList = firebaseDatabase.getReference(USERS).child(user.getUserName()).child(WORDLIST);
        wordList.setValue(words);
    }

    public static void getAllExercisesByTopicName(String topicName, ValueEventListener valueEventListener){
        DatabaseReference exercisesList = firebaseDatabase.getReference(EXERCISE).child(topicName);
        exercisesList.addValueEventListener(valueEventListener);
    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static StorageReference getImageStorageReference(String fileName){
        StorageReference storageReference = firebaseStorage.getReference(EXERCISE).child(fileName);
        return  storageReference;
    }

    public static void setImageToImageView(Context context, String fileName, ImageView imageView){
        StorageReference storageReference = firebaseStorage.getReference(EXERCISE).child(fileName);

        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful())
                {
                    Glide.with(context)
                            .load(task.getResult())
                            .apply(RequestOptions.noTransformation())
                            .into(imageView);
                }
            }
        });
    }
}
