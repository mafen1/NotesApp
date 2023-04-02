package com.example.notesapp.tree;

import java.util.ArrayList;
import java.util.Arrays;

public enum Catalog /*implements Item*/{
    OAK("ДУБ", "Дуб", new ArrayList<>(Arrays.asList("oak_1", "oak_2", "oak_3", "oak_4", "oak_5", "oak_6", "oak_7", "oak_8"))),
    BIRCH("БЕРЁЗА", "Берёза", new ArrayList<>(Arrays.asList("birch_1", "birch_2", "birch_3", "birch_4", "birch_5", "birch_6", "birch_7", "birch_8", "birch_9", "birch_10", "birch_11")));

    private ArrayList<String> stages;
    private float step;
    private final String nameTree;
    private final String imgTree;
    Catalog(String name, String img, ArrayList<String> stages){
        this.nameTree = name;
        this.imgTree = img;
        this.stages = stages;

        installStep();
    }


    public String nameTree(){
        return nameTree;
    }


    public String imgTree(){
        return imgTree;
    }


    //принимает значения от 0 до 100, возвращает название картинки
    public String showStage(int pcProgress){
        if (pcProgress < 0 ) pcProgress = 0;
        if (pcProgress > 100) pcProgress = 100;
        int index = (int) (pcProgress / step);
        if (index == 0) return stages.get(0);
        return stages.get(index-1);
    }
    public String showStage(int value, int max){
        int pcProgress = value * 100 / max;
        int index = (int) (pcProgress / step);
        if (index == 0) return stages.get(0);
        return stages.get(index-1);
    }


    private void installStep(){
        step = 1 / ((stages.size()) / 100f);
    }
}
