package sae.model;


import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Stack;

public class Case {
    private boolean sortie;

    private int posX;

    private ElementCase element;
    private StackPane leStack;
    private int posY;

    public Case(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.sortie = false;
        this.leStack = new StackPane();

    }


    public void setSortie(boolean sortie) {
        this.sortie = sortie;
    }
    public boolean getSortie() {
        return this.sortie;
    }

    public void ajouterElementCase(ElementCase elementCase){
        this.element = elementCase;
    }


    public void modifierElementCase(ElementCase newEle){
        this.element = newEle;
        this.element.changeCase(this);
    }
    public ElementCase getElement(){
        return this.element;
    }
    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setLeStack(StackPane stack){
        this.leStack = stack;
    }
    public StackPane getLeStack(){
        return this.leStack;
    }


}