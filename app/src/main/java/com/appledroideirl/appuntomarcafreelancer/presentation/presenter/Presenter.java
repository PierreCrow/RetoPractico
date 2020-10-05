package com.appledroideirl.appuntomarcafreelancer.presentation.presenter;

public interface Presenter<T> {

    void addView(T view);

    void removeView(T view);

}