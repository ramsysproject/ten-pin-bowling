package com.emramirez.bowling.model.factory;

import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.FrameScore;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class FrameScoreFactory {

    /**
     * Constructs a new instance of ${@link FrameScore}
     * @param partialScore
     * @param frame
     * @return the required instance
     */
    public FrameScore buildFrameScore(AtomicInteger partialScore, Frame frame) {
        return FrameScore.builder()
                .frame(frame)
                .scoreAt(partialScore.get())
                .build();
    }
}
