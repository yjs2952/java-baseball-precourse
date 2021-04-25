package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class BaseballGameTest {

    private BaseballGame game;

    @BeforeEach
    void setUp() {
        String gameNumber = "123";
        game = new BaseballGame(() -> Numbers.of(gameNumber));
    }

    @ParameterizedTest
    @CsvSource(value = {"234,0,2", "123,3,0", "132,1,2","456,0,0"})
    @DisplayName("유효한 숫자 입력 시 결과 반환")
    void getResultsTest(String numbers, int strikeCnt, int ballCnt) {

        // given
        Numbers inputNumbers = Numbers.of(numbers);

        // when
        Results results = game.start(inputNumbers);

        // then
        assertThat(results.getNumberOfStrike()).isEqualTo(strikeCnt);
        assertThat(results.getNumberOfBall()).isEqualTo(ballCnt);
    }

    @Test
    @DisplayName("게임 시작 전 isFinished() 메소드 호출 시 예외발생")
    void gameNotStartedExceptionTest(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> game.isClear())
                .withMessage("아직 게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("게임 시작 전 executeCommand() 메소드 호출 시 예외발생")
    void test(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> game.executeCommand("1"))
                .withMessage("아직 게임이 시작되지 않았습니다.");
    }
}