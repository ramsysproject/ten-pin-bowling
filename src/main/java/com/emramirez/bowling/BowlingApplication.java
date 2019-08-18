package com.emramirez.bowling;

import com.emramirez.bowling.model.BowlingMatch;
import com.emramirez.bowling.model.BowlingResult;
import com.emramirez.bowling.parser.ScoreFileParser;
import com.emramirez.bowling.presenter.BowlingResultPresenter;
import com.emramirez.bowling.score.BowlingScoreCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class BowlingApplication implements CommandLineRunner {

    private final ScoreFileParser scoreFileParser;
    private final BowlingScoreCalculator bowlingScoreCalculator;
    private final BowlingResultPresenter bowlingResultPresenter;

	public static void main(String[] args) {
		SpringApplication.run(BowlingApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
	    if (args.length == 0) {
	        throw new IllegalArgumentException("Missing filepath argument");
        }

		String filePath = args[0];
        System.out.println(filePath);

        FileInputStream fis = new FileInputStream(filePath);
        List<String> data = IOUtils.readLines(fis, "UTF-8");

        // Improve this orchestration
		Map playerGames = scoreFileParser.parseScoreLines(data.stream());
        BowlingMatch bowlingMatch = new BowlingMatch();
        bowlingMatch.setPlayerGames(playerGames);
        BowlingResult bowlingResult = bowlingScoreCalculator.apply(bowlingMatch);
        log.info(bowlingResultPresenter.present(bowlingResult));
	}
}
