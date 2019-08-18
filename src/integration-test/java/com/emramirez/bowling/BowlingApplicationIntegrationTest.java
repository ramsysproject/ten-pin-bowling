package com.emramirez.bowling;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BowlingApplication.class,
        initializers = ConfigFileApplicationContextInitializer.class)
public class BowlingApplicationIntegrationTest {

    @Autowired
    BowlingApplication bowlingApplication;

    @Test
    public void run_demoGameGiven_resultExpected() throws URISyntaxException, IOException {
        // arrange
        Path path = Paths.get(ClassLoader.getSystemResource("games/demogame.txt").toURI());

        // act
        bowlingApplication.run(path.toString());
    }

    @Test
    public void run_perfectGameGiven_resultExpected() throws URISyntaxException, IOException {
        // arrange
        Path path = Paths.get(ClassLoader.getSystemResource("games/perfectscoregame.txt").toURI());

        // act
        bowlingApplication.run(path.toString());
    }

    @Test
    public void run_zeroGameGiven_resultExpected() throws URISyntaxException, IOException {
        // arrange
        Path path = Paths.get(ClassLoader.getSystemResource("games/zeroscoregame.txt").toURI());

        // act
        bowlingApplication.run(path.toString());
    }
}
