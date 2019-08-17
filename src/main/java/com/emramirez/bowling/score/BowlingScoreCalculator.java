package com.emramirez.bowling.score;

import com.emramirez.bowling.model.BowlingMatch;
import com.emramirez.bowling.model.BowlingResult;

import java.util.function.Function;

@FunctionalInterface
public interface BowlingScoreCalculator extends Function<BowlingMatch, BowlingResult> {

}
