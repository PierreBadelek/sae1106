package sae.model;

public abstract class Vegetal extends ElementCase {
    protected int capaciteDeplacement;
    protected boolean estMange = false;
    protected boolean accessibilite;
    protected int cptRepousse = 0;

    public Vegetal(Case lc) {
        super(lc);

    }


    public void manger() {
        this.estMange = true;
    }

    public int getCapaciteDeplacement() {
        return capaciteDeplacement;
    }
    public int getCptRepousse() {
        return this.cptRepousse;
    }
    public boolean estMange() {
        return estMange;
    }

    public boolean getAccessibilite() {
        return accessibilite;
    }


    protected void checkRepousse(){
        if (this.estMange == true && this.cptRepousse+1 < 2){
            this.cptRepousse = this.cptRepousse + 1;
        } else if (this.cptRepousse >= 2 ){
            repousse();
        }
    }

    public void repousse(){
        this.estMange = false;
    }
}