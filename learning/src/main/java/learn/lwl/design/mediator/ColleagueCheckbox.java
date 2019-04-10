package learn.lwl.design.mediator;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ColleagueCheckbox extends Checkbox implements ItemListener, Colleague {
    public ColleagueCheckbox(String label, CheckboxGroup group, boolean state) {
        super(label, group, state);
        this.mediator = mediator;
    }

    private Mediator mediator;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;

    }

    @Override
    public void setColleagueEable(boolean enable) {
        setEnabled(enable);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        mediator.colleagueChanged();
    }
}
