package com.emramirez.bowling.processor;

import com.emramirez.bowling.model.Frame;

import java.util.ArrayList;
import java.util.List;

public class ScoreLinesProcessor {

    public List<Frame> process(List<Integer> scoreLines) {
        List<Frame> frames = new ArrayList<>();

        int initialRound = 1;
        frames = processFrames(scoreLines, frames, initialRound);

        return frames;
    }

    private List<Frame> processFrames(List<Integer> scoreLines, List<Frame> frames, int round) {
        int firstScore = scoreLines.get(0);

        if (round == 10) {
            return processLastRound(scoreLines, frames, round, firstScore);
        } else if (firstScore == 10) {
            round = processStrike(scoreLines, frames, round, firstScore);
        } else {
            round = processRegular(scoreLines, frames, round, firstScore);
        }

        processFrames(scoreLines, frames, round);
        return frames;
    }

    private List<Frame> processLastRound(List<Integer> scoreLines, List<Frame> frames, int round, int firstScore) {
        int secondScore = scoreLines.get(1);
        int thirdScore = thirdScoreApplies(firstScore, secondScore) ? scoreLines.get(2) : 0;
        frames.add(
                Frame.builder()
                        .firstPinFalls(firstScore)
                        .secondPinFalls(secondScore)
                        .thirdPinFalls(thirdScore)
                        .round(round).build()
        );
        return frames;
    }

    private boolean thirdScoreApplies(int firstScore, int secondScore) {
        return secondScore == 10 || (firstScore + secondScore == 10);
    }

    private int processRegular(List<Integer> scoreLines, List<Frame> frames, int round, int firstScore) {
        int secondScore = scoreLines.get(1);
        frames.add(Frame.builder().firstPinFalls(firstScore).secondPinFalls(secondScore).round(round).build());
        scoreLines.remove(0);
        scoreLines.remove(1);
        return ++round;
    }

    private int processStrike(List<Integer> scoreLines, List<Frame> frames, int round, int firstScore) {
        frames.add(Frame.builder().firstPinFalls(firstScore).secondPinFalls(0).round(round).build());
        scoreLines.remove(0);
        return ++round;
    }
}
