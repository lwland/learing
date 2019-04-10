package learn.lwl.design.prototype;

public class MessageBox extends CharProduct {
    private char boxChar;

    public MessageBox(char boxChar) {
        this.boxChar = boxChar;
    }

    @Override
    public void user(String str) {
        Integer len = str.getBytes().length;
        for (int i = 0; i < len + 4; i++) {
            System.out.print(boxChar);
        }
        System.out.println("");
        System.out.println(boxChar + " " + str + " " + boxChar);
        for (int i = 0; i < len + 4; i++) {
            System.out.print(boxChar);
        }
    }


}
