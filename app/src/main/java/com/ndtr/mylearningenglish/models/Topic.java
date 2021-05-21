package com.ndtr.mylearningenglish.models;

import java.util.List;

public class Topic {
    private String topicName;
    private List<String> wordList;
    private String shortDescription;
    private String longDescription;

    public Topic(){}

    public Topic(String topicName, List<String> wordList, String shortDescription,  String longDescription) {
        this.topicName = topicName;
        this.wordList = wordList;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}
