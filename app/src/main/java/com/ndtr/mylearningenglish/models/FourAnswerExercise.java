package com.ndtr.mylearningenglish.models;

import java.util.List;

public class FourAnswerExercise extends  Exercise{
    List<List<String>> answers;

    public FourAnswerExercise() {
    }

    public FourAnswerExercise(long type, List<String> questions, List<String> keys, List<List<String>> answers) {
        super(type, questions, keys);
        this.answers = answers;
    }

    public List<List<String>> getAnswers() {
        return answers;
    }

    public void setAnswers(List<List<String>> answers) {
        this.answers = answers;
    }
}
