package com.ndtr.mylearningenglish.models;


import java.util.List;

/**
 *param {int} type - 1 for Fill blank exercise, 2 for Fill blank with Image
 *                     3 for Four answer exercise, 4 for four answer exercise with Image
 *
*/

public abstract class Exercise {
    private long type;
    private List<String> questions;
    private List<String> keys;

    public Exercise(){}

        public Exercise(long type, List<String> questions, List<String> keys){
        this.type = type;
        this.questions = questions;
        this.keys = keys;
    }

    public long  getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }
}
