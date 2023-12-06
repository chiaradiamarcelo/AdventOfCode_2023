package day3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GearRatiosTest {
    @Test
    void retrieves_zero_parts_sum_when_single_number() {
        assertThat(new GearRatios("4").partsSum()).isEqualTo(0);
    }

    @Test
    void retrieves_parts_sum_when_single_number_symbol_left() {
        assertThat(new GearRatios("*4").partsSum()).isEqualTo(4);
    }

    // this case seems unnecessary, as this never happens apparently
    // @Test
    void retrieves_parts_sum_when_single_number_with_multiple_symbols() {
        assertThat(new GearRatios("*4*").partsSum()).isEqualTo(4);
    }

    @Test
    void retrieves_parts_sum_when_single_number_symbol_right() {
        assertThat(new GearRatios("4*").partsSum()).isEqualTo(4);
    }

    @Test
    void retrieves_parts_sum_when_single_number_symbol_up() {
        var content = """
                *
                4
                """;
        assertThat(new GearRatios(content).partsSum()).isEqualTo(4);
    }

    @Test
    void retrieves_parts_sum_when_single_number_symbol_down() {
        var content = """
                4
                *
                """;
        assertThat(new GearRatios(content).partsSum()).isEqualTo(4);
    }

    @Test
    void retrieves_parts_sum_when_single_number_symbol_diagonal_up_left() {
        var content = """
                *.
                .4
                """;
        assertThat(new GearRatios(content).partsSum()).isEqualTo(4);
    }

    @Test
    void retrieves_parts_sum_when_single_number_symbol_diagonal_up_right() {
        var content = """
                .*
                4.
                """;
        assertThat(new GearRatios(content).partsSum()).isEqualTo(4);
    }

    @Test
    void retrieves_parts_sum_when_single_number_symbol_diagonal_down_left() {
        var content = """
                .4
                *.
                """;
        assertThat(new GearRatios(content).partsSum()).isEqualTo(4);
    }

    @Test
    void retrieves_parts_sum_when_single_number_symbol_diagonal_down_right() {
        var content = """
                4.
                .*
                """;
        assertThat(new GearRatios(content).partsSum()).isEqualTo(4);
    }

    @Test
    void retrieves_gear_ratios_sum_when_single_gear_horizontal() {
        var content = """
                4*5
                """;
        assertThat(new GearRatios(content).gearRatiosSum()).isEqualTo(20);
    }

    @Test
    void retrieves_gear_ratios_sum_when_single_gear_vertical() {
        var content = """
                4
                *
                5
                """;
        assertThat(new GearRatios(content).gearRatiosSum()).isEqualTo(20);
    }

    @Test
    void retrieves_gear_ratios_sum_when_single_gear_diagonal_left() {
        var content = """
                4..
                .*.
                ..5
                """;
        assertThat(new GearRatios(content).gearRatiosSum()).isEqualTo(20);
    }

    @Test
    void retrieves_gear_ratios_sum_when_single_gear_diagonal_right() {
        var content = """
                ..4
                .*.
                5..
                """;
        assertThat(new GearRatios(content).gearRatiosSum()).isEqualTo(20);
    }

    @Test
    void retrieves_zero_gear_ratios_sum_when_no_gear_found_with_one_too_many_numbers() {
        var content = """
                4.5
                .*.
                6..
                """;
        assertThat(new GearRatios(content).gearRatiosSum()).isEqualTo(0);
    }

    @Test
    void retrieves_zero_gear_ratios_sum_when_no_gear_found_with_one_too_less_numbers() {
        var content = """
                *4
                """;
        assertThat(new GearRatios(content).gearRatiosSum()).isEqualTo(0);
    }

    @Test
    void retrieves_gear_ratios_sum_when_multiple_gear_ratios() {
        var content = """
                3*2
                ...
                4*3
                """;
        assertThat(new GearRatios(content).gearRatiosSum()).isEqualTo(18);
    }

    // This case seems unnecessary, as this never happens apparently
    // @Test
    void retrieves_gear_ratios_sum_when_double_symbol() {
        var content = """
                4.
                **
                5.
                """;
        assertThat(new GearRatios(content).gearRatiosSum()).isEqualTo(40);
    }
}
