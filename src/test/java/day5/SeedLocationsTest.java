package day5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SeedLocationsTest {
    @Test
    void one_seed_no_mapping_found() {
        var content = """
                seeds: 10 0

                seed-to-soil map:
                1 11 1
                """;
        assertThat(new SeedLocations(content).lowest()).isEqualTo(10);
    }

    @Test
    void one_seed_mapping_found_no_offset() {
        var content = """
                seeds: 11 0

                seed-to-soil map:
                43 11 1
                """;
        assertThat(new SeedLocations(content).lowest()).isEqualTo(43);
    }

    @Test
    void one_seed_no_mapping_found_one_greater_than_offset() {
        var content = """
                seeds: 12 0

                seed-to-soil map:
                43 11 1
                """;
        assertThat(new SeedLocations(content).lowest()).isEqualTo(12);
    }

    @Test
    void one_seed_mapping_found_with_offset() {
        var content = """
                seeds: 12 0

                seed-to-soil map:
                43 11 3
                """;
        assertThat(new SeedLocations(content).lowest()).isEqualTo(44);
    }

    @Test
    void one_seed_mapping_found_with_multiple_line() {
        var content = """
                seeds: 11 0

                seed-to-soil map:
                43 15 3
                25 11 3
                """;
        assertThat(new SeedLocations(content).lowest()).isEqualTo(25);
    }

    @Test
    void multiple_seeds_mapping() {
        var content = """
                seeds: 10 11

                seed-to-soil map:
                43 50 3
                """;
        assertThat(new SeedLocations(content).lowest()).isEqualTo(10);
    }

    @Test
    void multiple_mappings() {
        var content = """
                seeds: 11 0

                seed-to-soil map:
                20 10 3

                soil-to-fertilizer map:
                43 21 3
                """;
        assertThat(new SeedLocations(content).lowest()).isEqualTo(43);
    }

    @Test
    void seeds_in_pairs() {
        var content = """
                seeds: 10 5

                seed-to-soil map:
                50 10 2
                40 12 6
                """;
        assertThat(new SeedLocations(content).lowest()).isEqualTo(40);
    }
}
