package com.emramirez.bowling.score;

import com.emramirez.bowling.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class ClassicBowlingScoreCalculator implements BowlingScoreCalculator {

    public static final int LAST_ROUND = 10;
    public static final int PERFECT_SCORE = 10;

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
        if (frame.getRound() == LAST_ROUND) {
            partialScore.addAndGet(
                    (frame.isStrike() || frame.isSpare()) ? frame.sumFallenPins() : frame.sumRegularPlay()
            );
        } else {
            if (frame.isStrike() || frame.isSpare()) {
                partialScore.addAndGet(PERFECT_SCORE);
                partialScore.addAndGet(
                        frame.isStrike() ?
                                getNextFramesFallenPins(2, frame.getRound(), playerFrames) :
                                getNextFramesFallenPins(1, frame.getRound(), playerFrames)
                );
            } else {
                partialScore.addAndGet(frame.sumRegularPlay());
            }
        }

        log.debug("Round {} score is {}", frame.getRound(), partialScore.get());
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
    private int getNextFramesFallenPins(int shotNumbers, int round, List<Frame> frames) {
        int fallenPins;
        Frame nextFrame = findFrameByRound(round + 1, frames);

        if (round == 9 || !nextFrame.isStrike()) {
            fallenPins = IntStream.range(0, shotNumbers)
                    .map(i -> nextFrame.getFallenPinsAsList().get(i))
                    .sum();
        } else {
            fallenPins = IntStream.range(1, ++shotNumbers)
                    .mapToObj(i -> findFrameByRound(round + i, frames))
                    .mapToInt(Frame::getFirstPinFalls)
                    .sum();
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
