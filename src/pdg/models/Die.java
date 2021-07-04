package pdg.models;

import java.util.Random;

public class Die {
    private final static int sides = 6;
    private int top;
    public Die(){

        this.top = 0;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        if(top >= 0 && top <= sides)
            this.top = top;
    }
    public void roll(){
        Random random = new Random();
        int roll = random.nextInt(sides) + 1;
        this.top = roll;
    }
}
