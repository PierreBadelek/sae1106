package sae.model;

public class ElementCase {
    protected Case lacase;
    protected String imageURL;

    public ElementCase(Case c) {
        this.lacase = c;
        lacase.modifierElementCase(this);
    }

    public Case getLacase(){
        return this.lacase;
    }

    public void changeCase(Case newCase){
        this.lacase = newCase;
    }
    public String getImageURL(){return this.imageURL;}



}