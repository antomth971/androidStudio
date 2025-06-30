package com.example.arrondissement;


import java.util.ArrayList;

public class Quizz {
    public Quizz(int numRow, String question, ArrayList<String> reponse, String good_reponse) {
        this.numRow = numRow;
        this.question = question;
        this.reponse = reponse;
        this.good_reponse = good_reponse;
    }

    public int getNumRow() {
        return numRow;
    }

    public void setNumRow(int numRow) {
        this.numRow = numRow;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getReponse() {
        return reponse;
    }

    public void setReponse(ArrayList<String> reponse) {
        this.reponse = reponse;
    }

    public String getGood_reponse() {
        return good_reponse;
    }

    public void setGood_reponse(String good_reponse) {
        this.good_reponse = good_reponse;
    }

    private int numRow;
    private String question;
    private ArrayList<String> reponse;
    private String good_reponse;

    @Override
    public String toString() {
        return "Quizz{" +
                "numRow=" + numRow +
                ", question='" + question + '\'' +
                ", reponse=" + reponse +
                ", good_reponse='" + good_reponse + '\'' +
                '}';
    }
}