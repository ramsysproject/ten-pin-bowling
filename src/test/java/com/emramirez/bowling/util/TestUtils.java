package com.emramirez.bowling.util;

import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.FrameScore;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static List<FrameScore> buildFrameScores() {
        List<FrameScore> frameScores = new ArrayList<>();
        List<Frame> frames = buildFrames();
        frameScores.add(FrameScore.builder().frame(frames.get(0)).scoreAt(20).build());
        frameScores.add(FrameScore.builder().frame(frames.get(1)).scoreAt(39).build());
        frameScores.add(FrameScore.builder().frame(frames.get(2)).scoreAt(48).build());
        frameScores.add(FrameScore.builder().frame(frames.get(3)).scoreAt(66).build());
        frameScores.add(FrameScore.builder().frame(frames.get(4)).scoreAt(74).build());
        frameScores.add(FrameScore.builder().frame(frames.get(5)).scoreAt(84).build());
        frameScores.add(FrameScore.builder().frame(frames.get(6)).scoreAt(90).build());
        frameScores.add(FrameScore.builder().frame(frames.get(7)).scoreAt(120).build());
        frameScores.add(FrameScore.builder().frame(frames.get(8)).scoreAt(148).build());
        frameScores.add(FrameScore.builder().frame(frames.get(9)).scoreAt(167).build());

        return frameScores;
    }

    private static List<Frame> buildFrames() {
        List<Frame> frames = new ArrayList<>();
        frames.add(Frame.builder().round(1).firstPinFalls(10).build());
        frames.add(Frame.builder().round(2).firstPinFalls(7).secondPinFalls(3).build());
        frames.add(Frame.builder().round(3).firstPinFalls(9).secondPinFalls(0).build());
        frames.add(Frame.builder().round(4).firstPinFalls(10).build());
        frames.add(Frame.builder().round(5).firstPinFalls(0).secondPinFalls(8).build());
        frames.add(Frame.builder().round(6).firstPinFalls(8).secondPinFalls(2).build());
        frames.add(Frame.builder().round(7).firstPinFalls(0).secondPinFalls(6).build());
        frames.add(Frame.builder().round(8).firstPinFalls(10).build());
        frames.add(Frame.builder().round(9).firstPinFalls(10).build());
        frames.add(Frame.builder().round(10).firstPinFalls(10).secondPinFalls(8).thirdPinFalls(1).build());

        return frames;
    }
}
