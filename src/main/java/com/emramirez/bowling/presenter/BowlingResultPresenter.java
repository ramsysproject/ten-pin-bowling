package com.emramirez.bowling.presenter;

import com.emramirez.bowling.model.BowlingResult;
import com.emramirez.bowling.model.Frame;
import com.emramirez.bowling.model.FrameScore;
import com.emramirez.bowling.model.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BowlingResultPresenter {

    public static final String TAB = "\t";
    public static final String TEN = "10";
    public static final String STRIKE_SYMBOL = "X";
    public static final String PINFALLS = "Pinfalls";
    public static final String BREAK_LIKE = "\n";
    public static final String SPARE_SYMBOL = "/";
    public static final String FRAME = "Frame";

    public String present(BowlingResult bowlingResult) {
        Map<Player, List<FrameScore>> playerResults = bowlingResult.getPlayerResults();
        StringBuilder result = new StringBuilder();
        result.append(buildHeaderLine());

        playerResults.entrySet().stream()
                .forEach(e -> {
                    result.append(e.getKey().getName());
                    result.append(BREAK_LIKE);
                    result.append(PINFALLS);
                    StringBuilder pinfallLineBuilder = new StringBuilder();
                    buildPinfallsLine(e.getValue(), pinfallLineBuilder);
                    result.append(pinfallLineBuilder.toString().replace(TEN, STRIKE_SYMBOL));
                });

        return result.toString();
    }

    private void buildPinfallsLine(List<FrameScore> frameScores, StringBuilder input) {
        frameScores.stream().map(FrameScore::getFrame).forEach(frame -> {
            if (frame.isSpare()) {
                buildSpareRepresentation(input, frame);
            } else {
                if (frame.getRound() == 10) {
                    buildLastFrameRepresentation(input, frame);
                } else {
                    if (frame.isStrike()) {
                        buildStrikeRepresentation(input, frame);
                    } else {
                        buildRegularFrameRepresentation(input, frame);
                    }
                }
            }
        });
    }

    private void buildRegularFrameRepresentation(StringBuilder input, Frame frame) {
        input.append(TAB).append(frame.getFirstPinFalls()).append(TAB).append(frame.getSecondPinFalls());
    }

    private void buildLastFrameRepresentation(StringBuilder input, Frame frame) {
        buildRegularFrameRepresentation(input, frame);
        input.append(TAB).append(frame.getThirdPinFalls());
    }

    private void buildStrikeRepresentation(StringBuilder input, Frame frame) {
        input.append(TAB).append(TAB).append(frame.getFirstPinFalls());
    }

    private void buildSpareRepresentation(StringBuilder input, Frame frame) {
        input.append(TAB).append(frame.getFirstPinFalls()).append(TAB).append(SPARE_SYMBOL);
    }

    private String buildHeaderLine() {
        StringBuilder header = new StringBuilder();
        header.append(FRAME);
        IntStream.range(1, 11).forEach(i -> header.append(TAB).append(TAB).append(i));
        header.append(BREAK_LIKE);
        return header.toString();
    }
}
