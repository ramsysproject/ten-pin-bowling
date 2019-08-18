package com.emramirez.bowling.score;

import com.emramirez.bowling.model.*;
import com.emramirez.bowling.model.factory.FrameScoreFactory;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ClassicBowlingScoreCalculator implements BowlingScoreCalculator {

    public static final int LAST_FRAME = 10;
    public static final int PERFECT_SCORE = 10;

    private final FrameScoreFactory frameScoreFactory;

    /**
     *
     * @param bowlingMatch
     * @return
     */
    @Override
    public BowlingResult apply(BowlingMatch bowlingMatch) {
        Map<Player, List<FrameScore>> playerResults = new HashMap<>();
        Map<Player, List<Frame>> playerGames = bowlingMatch.getPlayerGames();

        AtomicInteger partialScore = new AtomicInteger();
        for (Player player: playerGames.keySet()) {
            List<Frame> playerFrames = playerGames.get(player);
            List<FrameScore> frameScores = playerFrames.stream()
                    .map(frame -> {
                        partialScore.set(calculateFrameScore(partialScore, frame, playerFrames));
                        return buildFrameScore(partialScore, frame);
                    })
                    .collect(Collectors.toList());

            playerResults.put(player, frameScores);
        }

        return BowlingResult.builder().playerResults(playerResults).build();
    }

    /**
     * This method calculates a given frame score. It holds the core calculation logic for a classic ten pin
     * bowling game.
     *
     * @param partialScore the partial score up until the given frame
     * @param frame holds the information of the frame which score we want to calculate
     * @return the new partial score
     */
    private int calculateFrameScore(AtomicInteger partialScore, Frame frame, List<Frame> playerFrames) {
        if (frame.getRound() == LAST_FRAME) {
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

        log.debug("Frame {} score is {}", frame.getRound(), partialScore.get());
        return partialScore.get();
    }

    /**
     * This method gets the amount of pins fallen in the next x shots, passed as parameter.
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
        return frameScoreFactory.buildFrameScore(partialScore, frame);
    }
}
