package com.example.collaborativecode.Model;

import java.util.HashMap;

public class FileSynchronizerMessage {
    
    private String linesMap;

    public FileSynchronizerMessage() {
        
    }

    public FileSynchronizerMessage(String linesMap) {
        this.linesMap = linesMap;
    }

    public String getLinesMap() {
        return this.linesMap;
    }

    public void setLine(String linesMap) {
        this.linesMap = linesMap;
    }
}
