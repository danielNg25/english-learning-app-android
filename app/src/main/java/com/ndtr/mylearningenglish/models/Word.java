package com.ndtr.mylearningenglish.models;

import java.util.List;

public class Word {
    private String wordName;
    private String meaning;
    private List<String> example;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Word(){}

    public Word(String wordName, String wordID, String meaning, String category, List<String> example) {
        this.wordName = wordName;
        this.meaning = meaning;
        this.example = example;
        this.category = category;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
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
