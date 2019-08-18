package com.emramirez.bowling.presenter;

import com.emramirez.bowling.model.BowlingResult;

@FunctionalInterface
public interface BowlingResultPresenter<T> {
    T present(BowlingResult bowlingResult);
}
