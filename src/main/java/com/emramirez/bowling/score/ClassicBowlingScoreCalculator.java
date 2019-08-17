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
            List<Frame> frameList = playerGames.get(player);
            List<FrameScore> frameScores = frameList.stream()
                    .map(frame -> buildFrameScore(partialScore, frame))
                    .collect(Collectors.toList());

            playerResults.put(player, frameScores);
        }

        return BowlingResult.builder().playerResults(playerResults).build();
    }

    private FrameScore buildFrameScore(AtomicInteger partialScore, Frame frame) {
        partialScore.set(calculateRoundScore(partialScore.get(), frame));

        FrameScore frameScore = new FrameScore();
        frameScore.setFrame(frame);
        frameScore.setScoreAt(partialScore.get());
        return frameScore;
    }

    /**
     * WIP
     * @param partialScore
     * @param frame
     * @return
     */
    private int calculateRoundScore(int partialScore, Frame frame) {
        if (frame.isStrike()) {
            return partialScore + 20;
        }

        return partialScore + frame.getFirstPinFalls() + frame.getSecondPinFalls();
    }
}
