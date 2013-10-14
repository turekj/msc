# coding=utf-8
from unittest.case import TestCase
from validation.word_validator import WordValidator


class WordValidatorTests(TestCase):
    def test_is_word_valid_method(self):
        word_validator = WordValidator()
        invalid_word = u'jeźdźże'
        valid_word = u'późność'

        is_invalid_word_valid = word_validator.is_word_valid(invalid_word)
        is_valid_word_valid = word_validator.is_word_valid(valid_word)

        self.assertEqual(False, is_invalid_word_valid)
        self.assertEqual(True, is_valid_word_valid)
