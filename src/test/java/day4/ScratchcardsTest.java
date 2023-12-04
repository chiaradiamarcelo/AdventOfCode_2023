package day4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScratchcardsTest {
    @Test
    void retrieves_zero_points_when_no_winning_numbers() {
        assertThat(new Scratchcards("Card 1: 41 | 83").points()).isEqualTo(0);
    }

    @Test
    void retrieves_one_point_when_single_winning_number() {
        assertThat(new Scratchcards("Card 1: 41 | 41").points()).isEqualTo(1);
    }

    @Test
    void retrieves_points_when_multiple_winning_numbers() {
        assertThat(new Scratchcards("Card 1: 83 86 | 83 86").points()).isEqualTo(2);
    }

    @Test
    void retrieves_points_when_winning_numbers_and_no_winners_mixed() {
        assertThat(new Scratchcards("Card 1: 83 | 83 87").points()).isEqualTo(1);
    }

    @Test
    void retrieves_points_when_single_digit_winning_number() {
        assertThat(new Scratchcards("Card 1:  6 |  6").points()).isEqualTo(1);
    }

    @Test
    void retrieves_points_when_multiple_cards() {
        var content = """
                Card 1: 41 | 41
                Card 2: 43 | 43
                """;
        assertThat(new Scratchcards(content).points()).isEqualTo(2);
    }

    @Test
    void retrieves_cards_count_when_no_winning_card() {
        assertThat(new Scratchcards("Card 1: 10 | 11").scratchcardsCount()).isEqualTo(1);
    }

    @Test
    void retrieves_cards_count_when_single_winner_card() {
        var content = """
                Card 1: 41 | 41
                Card 2: 10 | 11
                """;
        assertThat(new Scratchcards(content).scratchcardsCount()).isEqualTo(3);
    }

    @Test
    void retrieves_cards_count_when_multiple_winner_cards() {
        var content = """
                Card 1: 41 12 | 41 12
                Card 2: 11 | 11
                Card 3: 10 | 11
                """;
        assertThat(new Scratchcards(content).scratchcardsCount()).isEqualTo(7);
    }
}
