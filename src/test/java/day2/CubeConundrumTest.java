package day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CubeConundrumTest {
    private static final int RED_LIMIT = 1;
    private static final int BLUE_LIMIT = 1;
    private static final int GREEN_LIMIT = 1;

    @Test
    void retrieves_ids_sum_for_single_game_set() {
        var blueLimit = 1;
        assertThat(new CubeConundrum("Game 1: 1 blue").idsSum(RED_LIMIT, GREEN_LIMIT, blueLimit)).isEqualTo(1);
    }

    @Test
    void retrieves_zero_sum_for_single_game_and_single_color_set_when_blue_limit_reached() {
        var blueLimit = 2;
        assertThat(new CubeConundrum("Game 1: 3 blue").idsSum(RED_LIMIT, GREEN_LIMIT, blueLimit)).isEqualTo(0);
    }

    @Test
    void retrieves_zero_sum_for_single_game_and_single_color_set_when_red_limit_reached() {
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 2 red").idsSum(redLimit, GREEN_LIMIT, BLUE_LIMIT)).isEqualTo(0);
    }

    @Test
    void retrieves_zero_sum_for_single_game_and_single_color_set_when_green_limit_reached() {
        var greenLimit = 1;
        assertThat(new CubeConundrum("Game 1: 2 green").idsSum(RED_LIMIT, greenLimit, BLUE_LIMIT)).isEqualTo(0);
    }

    @Test
    void retrieves_zero_sum_for_single_game_and_multiple_color_set_when_limit_reached() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 2 green, 1 blue, 1 red").idsSum(redLimit, greenLimit, blueLimit))
                .isEqualTo(0);
    }

    @Test
    void retrieves_zero_sum_for_single_game_and_multiple_sets_when_limit_reached() {
        var greenLimit = 3;
        assertThat(new CubeConundrum("Game 1: 1 green; 4 green").idsSum(RED_LIMIT, greenLimit, BLUE_LIMIT))
                .isEqualTo(0);
    }

    @Test
    void retrieves_ids_sum_for_multiple_games() {
        var blueLimit = 1;
        var content = """
                Game 1: 1 blue
                Game 2: 1 blue
                """;
        assertThat(new CubeConundrum(content).idsSum(RED_LIMIT, GREEN_LIMIT, blueLimit)).isEqualTo(3);
    }

    @Test
    void retrieves_power_sum_for_single_game_green_color_set() {
        assertThat(new CubeConundrum("Game 1: 2 green").powerSum()).isEqualTo(2);
    }

    @Test
    void retrieves_power_sum_for_single_game_blue_color_set() {
        assertThat(new CubeConundrum("Game 1: 2 blue").powerSum()).isEqualTo(2);
    }

    @Test
    void retrieves_power_sum_for_single_game_red_color_set() {
        assertThat(new CubeConundrum("Game 1: 2 red").powerSum()).isEqualTo(2);
    }

    @Test
    void retrieves_power_sum_for_single_game_multiple_color_set() {
        assertThat(new CubeConundrum("Game 1: 2 green, 2 red").powerSum()).isEqualTo(4);
    }

    @Test
    void retrieves_power_sum_for_single_game_multiple_sets() {
        assertThat(new CubeConundrum("Game 1: 2 green, 2 red; 4 green").powerSum()).isEqualTo(8);
    }

    @Test
    void retrieves_power_sum_for_multiple_games() {
        var content = """
                Game 1: 3 blue
                Game 2: 2 green
                """;
        assertThat(new CubeConundrum(content).powerSum()).isEqualTo(5);
    }
}
