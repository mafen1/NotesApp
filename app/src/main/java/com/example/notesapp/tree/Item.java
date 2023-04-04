package com.example.notesapp.tree;

public interface Item {
    String name();
    String img();
    String showStage(int progress);
    String showStage(int value, int max);
}
