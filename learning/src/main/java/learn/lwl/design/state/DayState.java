package learn.lwl.design.state;

public class DayState implements State {
    private final static DayState signleton = new DayState();

    private DayState() {
    }

    public static DayState getInstance() {
        return signleton;
    }

    @Override
    public void doClock(Context context, int hour) {
        if (hour < 9 || hour > 17) {
            context.changeState(NightState.getInstance());

        }
    }

    @Override
    public void doUse(Context context) {
        context.recordLog("使用金库（白天）");
    }

    @Override
    public void doAlarm(Context context) {
        context.callSecurityCenter("按下警铃(白天)");
    }

    @Override
    public void doPhone(Context context) {
        context.callSecurityCenter("正常通话白天");
    }
}
