package day2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CubeConundrumTest {
    @Test
    void should_retrieve_ids_sum_for_single_game_set() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 1 blue", redLimit, greenLimit, blueLimit).idsSum()).isEqualTo(1);
    }

    @Test
    void should_retrieve_zero_sum_for_single_game_and_single_color_set_when_blue_limit_reached() {
        var greenLimit = 1;
        var blueLimit = 2;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 3 blue", redLimit, greenLimit, blueLimit).idsSum()).isEqualTo(0);
    }

    @Test
    void should_retrieve_zero_sum_for_single_game_and_single_color_set_when_red_limit_reached() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 2 red", redLimit, greenLimit, blueLimit).idsSum()).isEqualTo(0);
    }

    @Test
    void should_retrieve_zero_sum_for_single_game_and_single_color_set_when_green_limit_reached() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 2 green", redLimit, greenLimit, blueLimit).idsSum()).isEqualTo(0);
    }

    @Test
    void should_retrieve_zero_sum_for_single_game_and_multiple_color_set_when_limit_reached() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 2 green, 1 blue, 1 red", redLimit, greenLimit, blueLimit).idsSum())
                .isEqualTo(0);
    }

    @Test
    void should_retrieve_zero_sum_for_single_game_and_multiple_sets_when_limit_reached() {
        var greenLimit = 3;
        var blueLimit = 1;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 1 green; 4 green", redLimit, greenLimit, blueLimit).idsSum())
                .isEqualTo(0);
    }

    @Test
    void should_retrieve_ids_sum_for_multiple_games() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        var content = """
                Game 1: 1 blue
                Game 2: 1 red
                """;
        assertThat(new CubeConundrum(content, redLimit, greenLimit, blueLimit).idsSum()).isEqualTo(3);
    }

    @Test
    void should_retrieve_power_sum_for_single_game_green_color_set() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 2 green", redLimit, greenLimit, blueLimit).powerSum()).isEqualTo(2);
    }

    @Test
    void should_retrieve_power_sum_for_single_game_blue_color_set() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 2 blue", redLimit, greenLimit, blueLimit).powerSum()).isEqualTo(2);
    }

    @Test
    void should_retrieve_power_sum_for_single_game_red_color_set() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 2 red", redLimit, greenLimit, blueLimit).powerSum()).isEqualTo(2);
    }

    @Test
    void should_retrieve_power_sum_for_single_game_multiple_color_set() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 2 green, 2 red", redLimit, greenLimit, blueLimit).powerSum())
                .isEqualTo(4);
    }

    @Test
    void should_retrieve_power_sum_for_single_game_multiple_sets() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        assertThat(new CubeConundrum("Game 1: 2 green, 2 red; 4 green", redLimit, greenLimit, blueLimit).powerSum())
                .isEqualTo(8);
    }

    @Test
    void should_retrieve_power_sum_for_multiple_games() {
        var greenLimit = 1;
        var blueLimit = 1;
        var redLimit = 1;
        var content = """
                Game 1: 3 blue
                Game 2: 2 green
                """;
        assertThat(new CubeConundrum(content, redLimit, greenLimit, blueLimit).powerSum()).isEqualTo(5);
    }
}
