package com.emramirez.bowling.util;

import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.FrameScore;
import com.emramirez.bowling.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {

    public static List<FrameScore> buildFrameScores() {
        List<FrameScore> frameScores = new ArrayList<>();
        List<Frame> frames = build167GameFrames();
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

    public static List<Frame> build167GameFrames() {
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

    public static List<Frame> build80GameFrames() {
        List<Frame> frames = new ArrayList<>();
        frames.add(Frame.builder().round(1).firstPinFalls(5).secondPinFalls(4).build());
        frames.add(Frame.builder().round(2).firstPinFalls(5).secondPinFalls(4).build());
        frames.add(Frame.builder().round(3).firstPinFalls(5).secondPinFalls(4).build());
        frames.add(Frame.builder().round(4).firstPinFalls(5).secondPinFalls(4).build());
        frames.add(Frame.builder().round(5).firstPinFalls(5).secondPinFalls(4).build());
        frames.add(Frame.builder().round(6).firstPinFalls(5).secondPinFalls(4).build());
        frames.add(Frame.builder().round(7).firstPinFalls(5).secondPinFalls(4).build());
        frames.add(Frame.builder().round(8).firstPinFalls(5).secondPinFalls(4).build());
        frames.add(Frame.builder().round(9).firstPinFalls(5).secondPinFalls(4).build());
        frames.add(Frame.builder().round(10).firstPinFalls(5).secondPinFalls(4).build());

        return frames;
    }

    public static Player buildPlayer(String name) {
        return Player.builder().name(name).build();
    }

    public static List<Frame> buildPerfectFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            frames.add(Frame.builder().round(i).firstPinFalls(10).build());
        }
        frames.add(Frame.builder().round(10).firstPinFalls(10).secondPinFalls(10).thirdPinFalls(10).build());

        return frames;
    }

    public static List<Frame> buildNearPerfectFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            frames.add(Frame.builder().round(i).firstPinFalls(10).build());
        }
        frames.add(Frame.builder().round(10).firstPinFalls(10).secondPinFalls(10).thirdPinFalls(9).build());

        return frames;
    }

    public static List<Frame> buildZeroScoreFrames() {
        List<Frame> frames = IntStream.range(1, 11).mapToObj(i -> {
            return Frame.builder().round(i).firstPinFalls(0).secondPinFalls(0).build();
        }).collect(Collectors.toList());

        return frames;
    }
}
