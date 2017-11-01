package com.andrognito.patternlockview;

import android.graphics.Canvas;

import com.andrognito.patternlockview.listener.PatternLockViewListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangkui on 2017/10/31.
 */

abstract public class DotAdapter<T> implements PatternLockViewListener {
    private int count;
    private List<T> list;

    public DotAdapter(int count) {
        this.count = count;
        list = new ArrayList<>(count);
        createDotMap();
        if(list.size()!=count){
            throw new IllegalArgumentException("The map size not match dot count !");
        }
    }

    abstract public void onDraw(Canvas canvas, PatternLockView.Dot dot);

    abstract public void createDotMap();

    abstract public void onProgressIng(List<T> pattern);

    abstract public void onCompleted(List<T> pattern);

    public void add(T dotA) {
        list.add(dotA);
    }

    public T getItem(int id) {
        return id >= 0 && id < count ? list.get(id) : null;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onProgress(List<PatternLockView.Dot> pattern) {
        List<T> list = new ArrayList<>();
        for (PatternLockView.Dot d : pattern) {
            list.add(getItem(d.getId()));
        }
        onProgressIng(list);
    }

    @Override
    public void onComplete(List<PatternLockView.Dot> pattern) {
        List<T> list = new ArrayList<>();
        for (PatternLockView.Dot d : pattern) {
            list.add(getItem(d.getId()));
        }
        onCompleted(list);
    }

    @Override
    public void onCleared() {

    }
}
