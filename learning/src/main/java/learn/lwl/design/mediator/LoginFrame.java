package learn.lwl.design.mediator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends Frame implements ActionListener, Mediator {
    private ColleagueText textUser;
    private ColleagueText textPass;
    private ColleagueCheckbox checkboxGuest;
    private ColleagueCheckbox checkboxUser;
    private ColleagueButton submit;
    private ColleagueButton cancel;

    public LoginFrame(String title) {
        super(title);
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridLayout(4, 2));
        createColleague();
        add(checkboxGuest);
        add(checkboxUser);
        add(new Label("userName"));
        add(textUser);
        add(new Label("password"));
        add(textPass);
        add(submit);
        add(cancel);
        pack();
        show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        System.exit(0);
    }

    @Override
    public void createColleague() {
        CheckboxGroup checkboxGroup = new CheckboxGroup();
        checkboxGuest = new ColleagueCheckbox("Guest", checkboxGroup, false);
        checkboxUser = new ColleagueCheckbox("Login", checkboxGroup, false);
        textUser = new ColleagueText("", 10);
        textPass = new ColleagueText("", 10);
        submit = new ColleagueButton("ok");
        cancel = new ColleagueButton("cancel");
        checkboxGuest.setMediator(this);
        checkboxUser.setMediator(this);
        textPass.setMediator(this);
        textUser.setMediator(this);
        submit.setMediator(this);
        cancel.setMediator(this);
        checkboxGuest.addItemListener(checkboxGuest);
        checkboxUser.addItemListener(checkboxUser);
        textUser.addTextListener(textUser);
        textPass.addTextListener(textPass);
        submit.addActionListener(this);
        cancel.addActionListener(this);
    }

    @Override
    public void colleagueChanged() {
        if (checkboxGuest.getState()) {
            textUser.setColleagueEable(false);
            textPass.setColleagueEable(false);
            submit.setColleagueEable(false);
        } else {
            textUser.setColleagueEable(true);
            updatePassword();
        }

    }

    private void updatePassword() {
        if (textUser.getText().length() > 4) {
            textPass.setColleagueEable(true);
            if (textPass.getText().length() > 4) {
                submit.setColleagueEable(true);
            } else {
                submit.setColleagueEable(false);
            }
        }
    }
}
