package com.example.biaspediav4;

public class BiasModel {
    //Update variable list whenever you add column
    String biasName;
    String biasDefinition;
    String biasQuote;
    int image;


    //public BiasModel(String biasName, String biasDefinition, String biasExample, int image) {
    //update models related in MainActivity.java when you add more columns
    //update line 13
    public BiasModel(String biasName, String biasDefinition, String biasQuote, int image) {
        this.biasName = biasName;
        this.biasDefinition = biasDefinition;
        this.biasQuote = biasQuote;
        this.image = image;
    }

    //Add getter for every column you add
    public String getBiasName() {
        return biasName;
    }

    public String getBiasDefinition() {
        return biasDefinition;
    }

    public String getBiasQuote() {
        return biasQuote;
    }

    public int getImage() {
        return image;
    }
}
