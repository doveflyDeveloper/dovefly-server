package cn.dovefly.observer;

/**
 * Created by zhangjunge on 17/8/5.
 */
public class ObserverB extends IObserver
{

    public ObserverB(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( " B: "
                + Integer.toBinaryString( subject.getState() ) );
    }


}
