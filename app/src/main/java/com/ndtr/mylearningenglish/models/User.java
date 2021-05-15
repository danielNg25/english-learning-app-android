package com.ndtr.mylearningenglish.models;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.IgnoreExtraProperties;
import com.ndtr.mylearningenglish.firebase.FirebaseQuery;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class User {

    private String userName;
    private String password;
    private String fullName;
    private String email;

    private List<String> wordList;

    public User(){}

    public User(String userName, String password, String fullName, String email, List<String> wordList){
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.wordList = wordList;
    }

    public User(String userName, String password, String fullName, String email){
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }



    public boolean isEmpty(){
        if (userName.isEmpty()||password.isEmpty()){
            return false;
        }
        return true;
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
                FirebaseQuery.addWordToNoteBook(wordList);
//            Toast.makeText(context, "Thêm từ thành công", Toast.LENGTH_SHORT);
                return true;
            }
        }

        else{
            wordList = new ArrayList<>();
            wordList.add(wordID);
            FirebaseQuery.addWordToNoteBook(wordList);
//            Toast.makeText(context, "Thêm từ thành công", Toast.LENGTH_SHORT);
            return true;
        }
    }
}
