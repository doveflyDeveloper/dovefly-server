package cn.dovefly.observer;

/**
 * Created by zhangjunge on 17/8/5.
 */
public class ObserverA extends IObserver
{

    public ObserverA(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "A : "
                + Integer.toBinaryString( subject.getState() ) );
    }


}