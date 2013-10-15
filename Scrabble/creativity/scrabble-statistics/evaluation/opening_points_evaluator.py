# coding=utf-8
from validation.word_validator import WordValidator


class OpeningPointsEvaluator(object):
    def __init__(self):
        self.tile_points = {u'a': 1, u'ą': 5, u'b': 3, u'c': 2, u'ć': 6, u'd': 2, u'e': 1, u'ę': 5, u'f': 5, u'g': 3,
                            u'h': 3, u'i': 1, u'j': 3, u'k': 2, u'l': 2, u'ł': 3, u'm': 2, u'n': 1, u'ń': 7, u'o': 1,
                            u'ó': 5, u'p': 2, u'r': 1, u's': 1, u'ś': 5, u't': 2, u'u': 3, u'w': 1, u'y': 2, u'z': 1,
                            u'ź': 9, u'ż': 5}
        self.word_validator = WordValidator()

    def evaluate_max_points(self, word):
        if len(word) > 7 or len(word) < 1:
            return 0, 0

        if not self.word_validator.is_word_valid(word):
            return 0, 0

        max_sum = 0
        starting_point = 0

        for x in range(7 - len(word), 7):

            current_sum = 0

            for y in range(0, len(word)):
                letter = word[y]

                if letter in self.tile_points:
                    base_points = self.tile_points[letter]
                else:
                    base_points = 0

                if (x+y) == 2 or (x+y) == 10:
                    letter_bonus_points = 2 * base_points
                else:
                    letter_bonus_points = base_points

                current_sum += letter_bonus_points

            word_bonus_points = 2 * current_sum

            if len(word) == 7:
                word_bonus_points += 50

            if word_bonus_points > max_sum:
                starting_point = x

            max_sum = max(max_sum, word_bonus_points)

        return max_sum, starting_point
