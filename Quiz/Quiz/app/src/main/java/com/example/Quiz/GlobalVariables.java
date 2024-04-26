package com.example.Quiz;

public class GlobalVariables {
    private static GlobalVariables instance;
    private int bestscore=0;
    public static synchronized GlobalVariables getInstance() {
        if (instance == null) {
            instance = new GlobalVariables();
        }
        return instance;
    }
    public int getScore() {
        return bestscore;
    }
    public void setScore(int bestscore) {
        this.bestscore = bestscore;
    }
}

