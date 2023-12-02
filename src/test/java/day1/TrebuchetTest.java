package day1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrebuchetTest {
    @Test
    void should_retrieve_zero_when_empty_line() {
        assertThat(new Trebuchet("").calibrationValuesSum()).isEqualTo(0);
    }

    @Test
    void should_retrieve_double_number_when_single_number() {
        assertThat(new Trebuchet("1").calibrationValuesSum()).isEqualTo(11);
    }

    @Test
    void should_retrieve_sum_when_letter_after_number() {
        assertThat(new Trebuchet("1a").calibrationValuesSum()).isEqualTo(11);
    }

    @Test
    void should_retrieve_sum_when_letter_before_number() {
        assertThat(new Trebuchet("a1").calibrationValuesSum()).isEqualTo(11);
    }

    @Test
    void should_retrieve_sum_of_first_and_last_digits_number_when_only_two_numbers() {
        assertThat(new Trebuchet("12").calibrationValuesSum()).isEqualTo(12);
    }

    @Test
    void should_retrieve_sum_of_first_and_last_digits_number_when_more_than_two_numbers() {
        assertThat(new Trebuchet("123").calibrationValuesSum()).isEqualTo(13);
    }

    @Test
    void should_retrieve_sum_when_mixed_with_letters() {
        assertThat(new Trebuchet("a1bc2").calibrationValuesSum()).isEqualTo(12);
    }

    @Test
    void should_retrieve_sum_when_single_spelled_number() {
        assertThat(new Trebuchet("one").calibrationValuesSum()).isEqualTo(11);
    }

    @Test
    void should_retrieve_sum_when_mixed_spelled_and_literal_numbers() {
        assertThat(new Trebuchet("one2").calibrationValuesSum()).isEqualTo(12);
    }

    @Test
    void should_retrieve_sum_when_two_spelled_numbers() {
        assertThat(new Trebuchet("onetwo").calibrationValuesSum()).isEqualTo(12);
    }

    @Test
    void should_retrieve_sum_when_overlapping_spelled_numbers() {
        assertThat(new Trebuchet("twone").calibrationValuesSum()).isEqualTo(21);
    }

    @Test
    void should_retrieve_calibration_value_when_multiple_lines_document() {
        var calibrationDocument = new Trebuchet("""
                11
                22
                """);
        assertThat(calibrationDocument.calibrationValuesSum()).isEqualTo(33);
    }
}
