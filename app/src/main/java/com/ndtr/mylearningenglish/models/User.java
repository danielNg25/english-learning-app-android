package com.ndtr.mylearningenglish.models;

import com.google.firebase.database.IgnoreExtraProperties;
import com.ndtr.mylearningenglish.firebase.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* @param type: 1 for Student account, 2 for Teacher account

 */

@IgnoreExtraProperties
public class User {

    private String userName;
    private String password;
    private String fullName;
    private String email;

    private Map<String, Long> exercises;
    private List<String> wordList;

    public User(){}

    public User(String userName, String password, String fullName, String email, Map<String, Long> exercises, List<String> wordList){
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.wordList = wordList;
        this.exercises = exercises;
    }

    public User(String userName, String password, String fullName, String email){
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }

    public int countExercisesScore(){
        int sum = 0;
        if (this.exercises == null) return 0;
        for (String key: this.exercises.keySet()){
            sum += this.exercises.get(key);
        }
        return sum;
    }

    public boolean isEmpty(){
        if (userName.isEmpty()||password.isEmpty()){
            return false;
        }
        return true;
    }

    public Map<String, Long> getExercises() {
        return exercises;
    }

    public void setExercises(Map<String, Long> exercises) {
        this.exercises = exercises;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // return true when not exist
    // return false when exist
    public boolean checkIfNotExistWord(String wordID){
        if (this.wordList != null) {
            if (this.wordList.contains(wordID)){
//            Toast.makeText(context, "Từ đã tồn tại", Toast.LENGTH_SHORT);
                return false;
            }
            else{
                wordList.add(wordID);
                FirebaseAuth.addWordToNoteBook(wordList);
//            Toast.makeText(context, "Thêm từ thành công", Toast.LENGTH_SHORT);
                return true;
            }
        }

        else{
            wordList = new ArrayList<>();
            wordList.add(wordID);
            FirebaseAuth.addWordToNoteBook(wordList);
//            Toast.makeText(context, "Thêm từ thành công", Toast.LENGTH_SHORT);
            return true;
        }
    }
}
