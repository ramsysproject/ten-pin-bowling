package com.emramirez.bowling.score;

import com.emramirez.bowling.model.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ClassicBowlingScoreCalculator implements BowlingScoreCalculator {

    @Override
    public BowlingResult apply(BowlingMatch bowlingMatch) {
        Map<Player, List<FrameScore>> playerResults = new HashMap<>();
        Map<Player, List<Frame>> playerGames = bowlingMatch.getPlayerGames();

        AtomicInteger partialScore = new AtomicInteger();
        for (Player player: playerGames.keySet()) {
            List<Frame> playerFrames = playerGames.get(player);
            List<FrameScore> frameScores = playerFrames.stream()
                    .map(frame -> {
                        partialScore.set(calculateRoundScore(partialScore, frame, playerFrames));
                        return buildFrameScore(partialScore, frame);
                    })
                    .collect(Collectors.toList());

            playerResults.put(player, frameScores);
        }

        return BowlingResult.builder().playerResults(playerResults).build();
    }

    /**
     * WIP
     * @param partialScore
     * @param frame
     * @return
     */
    private int calculateRoundScore(AtomicInteger partialScore, Frame frame, List<Frame> playerFrames) {
        if (frame.getRound() == 10) {
            if (frame.isStrike() || frame.isSpare()) {
                partialScore.addAndGet(frame.sumFallenPins());
            } else {
                partialScore.addAndGet(frame.getFirstPinFalls() + frame.getSecondPinFalls());
            }
        } else {
            if (frame.isStrike()) {
                partialScore.addAndGet(10);
                Frame nextFrame = findFrameByRound(frame.getRound() + 1, playerFrames);
                if (nextFrame.isStrike()) {
                    partialScore.addAndGet(10);
                    if (nextFrame.getRound() == 10) {
                        partialScore.addAndGet(nextFrame.getSecondPinFalls());
                    } else {
                        Frame otherFrame = findFrameByRound(nextFrame.getRound() + 1, playerFrames);
                        partialScore.addAndGet(otherFrame.getFirstPinFalls());
                    }
                } else {
                    partialScore.addAndGet(nextFrame.getFirstPinFalls() + nextFrame.getSecondPinFalls());
                }
            } else if (frame.isSpare()) {
                partialScore.addAndGet(10);
                partialScore.addAndGet(getNextFallenPins(1, frame.getRound(), playerFrames));
            } else {
                partialScore.addAndGet(frame.getFirstPinFalls());
                partialScore.addAndGet(frame.getSecondPinFalls());
            }
        }

        System.out.println(String.format("Round %d score is %d", frame.getRound(), partialScore.get()));

        return partialScore.get();
    }

    /**
     * This methods gets the amount of pins fallen in the next x shots, passed as parameter.
     * 
     * @param shotNumbers the number of shots ahead we want to analyze
     * @param round the current frame round
     * @param frames the player frames
     * @return the amount of fallen pins for the asked shots
     */
    private int getNextFallenPins(int shotNumbers, int round, List<Frame> frames) {
        int fallenPins = 0;
        for (int i = 1; i <= shotNumbers; i++) {
            Frame nextFrame = findFrameByRound(round + i, frames);
            fallenPins += nextFrame.getFirstPinFalls();
        }
        return fallenPins;
    }

    private Frame findFrameByRound(int i, List<Frame> playerFrames) {
        return playerFrames
                .stream()
                .filter(frame -> frame.getRound() == i)
                .findFirst().get();
    }

    private FrameScore buildFrameScore(AtomicInteger partialScore, Frame frame) {
        FrameScore frameScore = new FrameScore();
        frameScore.setFrame(frame);
        frameScore.setScoreAt(partialScore.get());
        return frameScore;
    }
}
