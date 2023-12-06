package day6;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoatsRaceTest {
    @Test
    void single_race_single_no_way_to_win_part_1() {
        var content = """
                Time:      1
                Distance:  2
                """;
        assertThat(new BoatsRace(content).winnersListPart1()).containsExactly(0L);
    }

    @Test
    void single_race_multiple_ways_to_win_part_1() {
        var content = """
                Time:      7
                Distance:  9
                """;
        assertThat(new BoatsRace(content).winnersListPart1()).containsExactly(4L);
    }

    @Test
    void multiple_races_part_1() {
        var content = """
                Time:      3   4
                Distance:  1   2
                """;
        assertThat(new BoatsRace(content).winnersListPart1()).containsExactly(2L, 3L);
    }

    @Test
    void winners_count_part_1() {
        var content = """
                Time:      3   4
                Distance:  1   2
                """;
        assertThat(new BoatsRace(content).winnersCountPart1()).isEqualTo(6);
    }

    @Test
    void single_race_part_2() {
        var content = """
                Time:      1   5
                Distance:  4   0
                """;
        assertThat(new BoatsRace(content).winnersCountPart2()).isEqualTo(8);
    }

    @Test
    void all() {
        var content = """
                Time:        35     69     68     87
                Distance:   213   1168   1086   1248
                """;
        assertThat(new BoatsRace(content).winnersCountPart2()).isEqualTo(20537782);
    }
}
