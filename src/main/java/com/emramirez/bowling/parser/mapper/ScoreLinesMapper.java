package com.emramirez.bowling.parser.mapper;

import com.emramirez.bowling.enums.RoundEnum;
import com.emramirez.bowling.model.Frame;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class purpose is to map score lines into it´s object representation ${@link Frame}
 */
@Service
public class ScoreLinesMapper {

    public static final int TEN_PINS = 10;

    /**
     * Maps a list of integer representing score lines into a list of ${@link Frame}
     * @param scoreLines the score lines to map
     * @return a list of frames representing the score lines
     */
    public List<Frame> map(List<Integer> scoreLines) {
        List<Frame> frames = new ArrayList<>();
        frames = processFrames(scoreLines, frames, RoundEnum.FIRST_ROUND.getRoundNumber());
        return frames;
    }

    private List<Frame> processFrames(List<Integer> scoreLines, List<Frame> frames, int round) {
        int firstScore = scoreLines.get(0);

        if (round == RoundEnum.LAST_ROUND.getRoundNumber()) {
            return processLastRound(scoreLines, frames, round, firstScore);
        } else if (firstScore == TEN_PINS) {
            round = processStrike(frames, round, firstScore);
            scoreLines = removeElements(scoreLines, 1);
        } else {
            round = processRegular(scoreLines, frames, round, firstScore);
            scoreLines = removeElements(scoreLines, 2);
        }

        processFrames(scoreLines, frames, round);
        return frames;
    }

    private List<Integer> removeElements(List<Integer> scoreLines, int i) {
        return scoreLines.stream().skip(i).collect(Collectors.toList());
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
        return firstScore == TEN_PINS || (firstScore + secondScore == TEN_PINS);
    }

    private int processRegular(List<Integer> scoreLines, List<Frame> frames, int round, int firstScore) {
        int secondScore = scoreLines.get(1);
        frames.add(Frame.builder().firstPinFalls(firstScore).secondPinFalls(secondScore).round(round).build());
        return ++round;
    }

    private int processStrike(List<Frame> frames, int round, int firstScore) {
        frames.add(Frame.builder().firstPinFalls(firstScore).secondPinFalls(0).round(round).build());
        return ++round;
    }
}
