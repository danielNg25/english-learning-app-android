package com.ndtr.mylearningenglish.models;

import java.util.List;

public class Word {
    private String wordName;
    private String wordID;
    private String meaning;
    private List<String> example;

    public Word(){}

    public Word(String wordName, String wordID, String meaning, List<String> example) {
        this.wordName = wordName;
        this.wordID = wordID;
        this.meaning = meaning;
        this.example = example;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getWordID() {
        return wordID;
    }

    public void setWordID(String wordID) {
        this.wordID = wordID;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public List<String> getExample() {
        return example;
    }

    public void setExample(List<String> example) {
        this.example = example;
    }
}
