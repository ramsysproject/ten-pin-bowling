package com.emramirez.bowling.model.factory;

import com.emramirez.bowling.model.Frame;
import org.springframework.stereotype.Component;

@Component
public class FrameFactory {

    /**
     * Constructs a new instance of ${@link Frame}
     *
     * @param round the given round
     * @param firstScore the first shot score
     * @return the required instance
     */
    public Frame buildFrameScore(int round, Integer firstScore) {
        Frame frame = Frame.builder()
                .round(round)
                .firstPinFalls(firstScore)
                .build();

        return frame;
    }

    /**
     * Constructs a new instance of ${@link Frame}
     *
     * @param round the given round
     * @param firstScore the first shot score
     * @param secondScore the second shot score
     * @return the required instance
     */
    public Frame buildFrameScore(int round, Integer firstScore, Integer secondScore) {
        Frame frame = buildFrameScore(round, firstScore);
        frame.setSecondPinFalls(secondScore);

        return frame;
    }

    /**
     * Constructs a new instance of ${@link Frame}
     *
     * @param round the given round
     * @param firstScore the first shot score
     * @param secondScore the second shot score
     * @param thirdScore the third shot score
     * @return the required instance
     */
    public Frame buildFrameScore(int round, Integer firstScore, Integer secondScore, Integer thirdScore) {
        Frame frame = buildFrameScore(round, firstScore, secondScore);
        frame.setThirdPinFalls(thirdScore);

        return frame;
    }
}
