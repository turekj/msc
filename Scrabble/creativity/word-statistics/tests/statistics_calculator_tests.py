from unittest.case import TestCase
from calculator.statistics_calculator import StatisticsCalculator


class StatisticsCalculatorTests(TestCase):
    def test_word_counting(self):
        words = ['first', 'second', 'third']
        calculator = StatisticsCalculator(words)

        words_count = calculator.get_words_count()

        self.assertEqual(3, words_count)

    def test_letter_probability_calculation(self):
        words = ['come', 'and', 'get', 'some']
        calculator = StatisticsCalculator(words)

        e_probability = calculator.get_letter_probability('e')
        o_probability = calculator.get_letter_probability('o')
        g_probability = calculator.get_letter_probability('g')

        self.assertEqual(3/14, e_probability)
        self.assertEqual(2/14, o_probability)
        self.assertEqual(1/14, g_probability)
