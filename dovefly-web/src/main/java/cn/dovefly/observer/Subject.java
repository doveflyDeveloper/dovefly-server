package cn.dovefly.observer;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjunge on 17/8/5.
 */
public class Subject {
    List<IObserver> lists = new ArrayList<>();
    private int state;

    public void attach(IObserver iObserver) {
        lists.add(iObserver);
    }

    public void notifyAllObser() {
        for (IObserver iObserver : lists) {
            iObserver.update();
        }

    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObser();
    }


    public static void main(String[] args) {
        Subject subject = new Subject();
        new ObserverA(subject);
        new ObserverB(subject);


        subject.setState(1);

    }
}
