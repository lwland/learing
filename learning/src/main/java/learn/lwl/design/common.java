package learn.lwl.design;

import learn.lwl.design.template.AbstractDisplay;
import learn.lwl.design.template.StringDisPlay;

public class common {
    public static void main(String[] args) {
        AbstractDisplay abstractDisplay = new StringDisPlay("hello world");
        abstractDisplay.display();
    }
}
