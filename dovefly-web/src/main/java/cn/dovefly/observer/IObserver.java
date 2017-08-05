package cn.dovefly.observer;

/**
 * Created by zhangjunge on 17/8/5.
 */
public abstract class IObserver {
    protected Subject subject;
    public abstract void update();
}
