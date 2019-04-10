package learn.lwl.design.prototype;

public class UnderlinePen extends CharProduct {
    private char ulChar;

    public UnderlinePen(char ulChar) {
        this.ulChar = ulChar;
    }

    @Override
    public void user(String str) {
        System.out.println(str);
        int len = str.getBytes().length;
        for (int i = 0; i < len; i++) {
            System.out.print(ulChar);
        }
    }

}
