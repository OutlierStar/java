package demo16;

public class myaction {
    private char ch;
    private int j;

    public myaction(char ch, int j) {
        this.ch = ch;
        this.j = j;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public int getJ() {
        return j;
    }

    public void setI(int j) {
        this.j = j;
    }

    public boolean isAcc() {
        return ch == 'a' && j == 0;
    }

    public int istype() {
        if(isAcc())
        return 3;
        return ch == 'S'? 1:2;
    }

}