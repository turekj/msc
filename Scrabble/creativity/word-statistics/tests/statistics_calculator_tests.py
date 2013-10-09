from unittest.case import TestCase
from calculator.statistics_calculator import StatisticsCalculator


class StatisticsCalculatorTests(TestCase):
    def test_word_counting(self):
        words = ['first', 'second', 'third']
        calculator = StatisticsCalculator()

        calculator.process_words(words)
        words_count = calculator.get_words_count()

        self.assertEqual(3, words_count)

    def test_letter_probability_calculation(self):
        words = ['come', 'and', 'get', 'some']
        calculator = StatisticsCalculator()

        calculator.process_words(words)
        e_probability = calculator.get_letter_probability('e')
        o_probability = calculator.get_letter_probability('o')
        g_probability = calculator.get_letter_probability('g')

        self.assertEqual(3/14, e_probability)
        self.assertEqual(2/14, o_probability)
        self.assertEqual(1/14, g_probability)

    def test_ngram_count_calculation(self):
        words = ['come', 'and', 'get', 'some', 'or', 'go', 'home']
        calculator = StatisticsCalculator()

        calculator.process_words(words)
        ome_count = calculator.get_ngram_count('ome')
        e_count = calculator.get_ngram_count('e')
        and_count = calculator.get_ngram_count('and')
        rome_count = calculator.get_ngram_count('rome')

        self.assertEqual(3, ome_count)
        self.assertEqual(4, e_count)
        self.assertEqual(1, and_count)
        self.assertEqual(0, rome_count)

    def test_most_probable_ngram_calculation(self):
        words = ['come', 'and', 'get', 'some', 'random', 'or', 'go', 'home']
        calculator = StatisticsCalculator()

        calculator.process_words(words)
        most_probable_bigrams = calculator.get_most_probable_ngrams(2)
        most_probable_trigrams = calculator.get_most_probable_ngrams(3)

        self.assertEqual('om', most_probable_bigrams[0][0])
        self.assertEqual(4, most_probable_bigrams[0][1])
        self.assertEqual('ome', most_probable_trigrams[0][0])
        self.assertEqual(3, most_probable_trigrams[0][1])
        self.assertEqual('and', most_probable_trigrams[1][0])
        self.assertEqual(2, most_probable_trigrams[1][1])
