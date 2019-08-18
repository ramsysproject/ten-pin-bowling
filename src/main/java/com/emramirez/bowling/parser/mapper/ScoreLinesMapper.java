package com.emramirez.bowling.parser.mapper;

import com.emramirez.bowling.enums.RoundEnum;
import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.factory.FrameFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class purpose is to map score lines into it´s object representation ${@link Frame}.
 */
@Service
@RequiredArgsConstructor
public class ScoreLinesMapper {

    public static final int TEN_PINS = 10;

    private final FrameFactory frameFactory;

    /**
     * Maps a list of integer representing score lines into a list of ${@link Frame}.
     *
     * @param scoreLines the score lines to map
     * @return a list of frames representing the score lines
     */
    public List<Frame> map(List<Integer> scoreLines) {
        return processFrames(scoreLines, new ArrayList<>(), RoundEnum.FIRST_ROUND.getNumber());
    }

    /**
     * This is a recursive function what processes the score lines and produces the frames.
     * Every iteration the score lines are reduced and a new ${@link Frame} object is added to the result list.
     *
     * @param scoreLines the score lines to process
     * @param frames the list of frames to return
     * @param round the iteration controller, round number
     * @return a list of frames representing the score lines
     */
    private List<Frame> processFrames(List<Integer> scoreLines, List<Frame> frames, int round) {
        int firstScore = scoreLines.get(0);

        if (round == RoundEnum.LAST_ROUND.getNumber()) {
            return mapLastRound(scoreLines, frames, round);
        } else if (firstScore == TEN_PINS) {
            round = mapStrike(scoreLines, frames, round);
            scoreLines = removeElements(scoreLines, 1);
            return processFrames(scoreLines, frames, round);
        } else {
            round = mapRegular(scoreLines, frames, round);
            scoreLines = removeElements(scoreLines, 2);
            return processFrames(scoreLines, frames, round);
        }
    }

    private List<Frame> mapLastRound(List<Integer> scoreLines, List<Frame> frames, int round) {
        int firstScore = scoreLines.get(0);
        int secondScore = scoreLines.get(1);
        int thirdScore = thirdScoreApplies(firstScore, secondScore) ? scoreLines.get(2) : 0;
        frames.add(frameFactory.buildFrame(round, firstScore, secondScore, thirdScore));
        return frames;
    }

    private int mapRegular(List<Integer> scoreLines, List<Frame> frames, int round) {
        frames.add(frameFactory.buildFrame(round, scoreLines.get(0), scoreLines.get(1)));
        return ++round;
    }

    private int mapStrike(List<Integer> scoreLines, List<Frame> frames, int round) {
        frames.add(frameFactory.buildFrame(round, scoreLines.get(0)));
        return ++round;
    }

    private boolean thirdScoreApplies(int firstScore, int secondScore) {
        return (firstScore == TEN_PINS) || (firstScore + secondScore == TEN_PINS);
    }

    private List<Integer> removeElements(List<Integer> scoreLines, int i) {
        return scoreLines.stream().skip(i).collect(Collectors.toList());
    }
}
