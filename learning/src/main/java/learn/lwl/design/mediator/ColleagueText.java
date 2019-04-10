package learn.lwl.design.mediator;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class ColleagueText extends TextField implements TextListener, Colleague {
    private Mediator mediator;

    public ColleagueText(String text, int columns) {
        super(text, columns);
        this.mediator = mediator;
    }

    @Override
    public void textValueChanged(TextEvent e) {
        mediator.colleagueChanged();
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEable(boolean enable) {
        setEnabled(enable);
        setBackground(enable ? Color.WHITE : Color.GRAY);
    }

}
