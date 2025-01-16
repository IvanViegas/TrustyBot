
import Controllers.NativeHooks.NativeHookCatcher;
import Models.KeyBtn;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import repository.KeycodesRepository;
import repository.btnRepository;

public class CatchKeys extends Thread{
    int timeLimit = 15000;
    boolean finishedFlag = true;

    public CatchKeys(int timeLimit, boolean finishedFlag) {
        this.timeLimit = timeLimit;
        this.finishedFlag = finishedFlag;
    }

    public void run() {
        try {
            GlobalScreen.registerNativeHook();
            NativeHookCatcher hook = new NativeHookCatcher();
            GlobalScreen.addNativeKeyListener(hook);

            long starTime = System.currentTimeMillis();
            long timeElapsed = 0;
            while (timeElapsed <= timeLimit) {
                Thread.sleep(10);
                timeElapsed = System.currentTimeMillis() - starTime;
                if (hook.isFlag()){
                    btnRepository.saveKey(new KeyBtn(1000, hook.getKeyCode(), (int) timeElapsed, hook.getName()));
                    hook.setFlag(false);
                }
            }
            finishedFlag = false;
            System.out.println("Finished flag on timer: " + isFinishedFlag());
            GlobalScreen.removeNativeKeyListener(hook);
        } catch (NativeHookException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean isFinishedFlag() {
        return finishedFlag;
    }

    public void setFinishedFlag(boolean finishedFlag) {
        this.finishedFlag = finishedFlag;
    }

    public static void main(String[] args) {

        CatchKeys timer = new CatchKeys(99999999, true);
        KeycodesRepository.initList();
        timer.start();
    }
}

