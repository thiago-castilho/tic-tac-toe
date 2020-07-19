package tictactoe;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Collectors;

public class Game {

    private ChromeDriver driver;
    private Board board;

    private int playerScore, tieScore, computerScore;
    private By restartLink = By.className("restart");

    public Game() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.get("https://playtictactoe.org/");

        this.board = new Board(this.driver);
        updateScores();
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean isOver() {
        return this.driver.findElement(this.restartLink).isDisplayed();
    }

    public boolean isThereAWinner(int winningScore) {
        updateScores();
        return this.playerScore >= winningScore || this.computerScore >= winningScore;

    }

    public void updateScores() {

        // Getting the scores from the browser
        var scores = this.driver.findElementsByClassName("score")
                .stream()
                .map(WebElement::getText)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        this.playerScore = scores.get(0);
        this.tieScore = scores.get(1);
        this.computerScore = scores.get(2);
    }

    public Board restart() {
        this.driver.findElement(this.restartLink).click();
        this.board = new Board(this.driver);
        return this.board;
    }

    public void printResults() {
        int initialScore = this.playerScore;
        int initialTieScore = this.tieScore;

        updateScores();

        if (this.playerScore > initialScore) {
            System.out.println("Yeeeah, you won! 🎉");
        } else if (this.tieScore > initialScore) {
            System.out.println("It's a tie. 😒");
        } else {
            System.out.println("Oh no, you lose! 😭");
        }

    }

    public void end() {
        this.driver.quit();
        System.exit(0);
    }
}
