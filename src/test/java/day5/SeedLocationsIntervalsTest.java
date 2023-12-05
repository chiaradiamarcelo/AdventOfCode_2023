package day5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SeedLocationsIntervalsTest {
    @Test
    void no_overlapping_interval() {
        var content = """
                seeds: 10 2

                seed-to-soil map:
                1 13 1
                """;
        assertThat(new SeedLocationsIntervals(content).lowest()).isEqualTo(10);
    }

    @Test
    void full_overlapping_interval() {
        var content = """
                seeds: 10 2

                seed-to-soil map:
                1 10 2
                """;
        assertThat(new SeedLocationsIntervals(content).lowest()).isEqualTo(1);
    }

    @Test
    void overlapping_interval_upperbound_leftovers() {
        var content = """
                seeds: 10 5

                seed-to-soil map:
                50 10 2
                """;
        assertThat(new SeedLocationsIntervals(content).lowest()).isEqualTo(12);
    }

    @Test
    void overlapping_interval_lowerbound_leftovers() {
        var content = """
                seeds: 8 4

                seed-to-soil map:
                50 10 2
                """;
        assertThat(new SeedLocationsIntervals(content).lowest()).isEqualTo(8);
    }

    @Test
    void overlapping_interval_lowerbound() {
        var content = """
                seeds: 8 4

                seed-to-soil map:
                3 10 2
                """;
        assertThat(new SeedLocationsIntervals(content).lowest()).isEqualTo(3);
    }

    @Test
    void overlapping_interval_upperbound_leftovers_dropped_to_next_line() {
        var content = """
                seeds: 10 5

                seed-to-soil map:
                50 10 2
                45 12 3
                """;
        assertThat(new SeedLocationsIntervals(content).lowest()).isEqualTo(45);
    }

    @Test
    void overlapping_interval_lowerbound_leftovers_dropped_to_next_line() {
        var content = """
                seeds: 8 4

                seed-to-soil map:
                50 10 2
                45  8 2
                """;
        assertThat(new SeedLocationsIntervals(content).lowest()).isEqualTo(45);
    }

    @Test
    void multiple_seed_ranges() {
        var content = """
                seeds: 10 3 15 3

                seed-to-soil map:
                50 10 3
                45 15 2
                """;
        assertThat(new SeedLocationsIntervals(content).lowest()).isEqualTo(17);
    }

    @Test
    void multiple_mappings() {
        var content = """
                seeds: 10 1

                seed-to-soil map:
                50 10 1

                soil-to-fertilizer map:
                43 50 1
                """;
        assertThat(new SeedLocationsIntervals(content).lowest()).isEqualTo(43);
    }
}
