# coding=utf-8
class OpeningPointsEvaluator(object):
    def __init__(self):
        self.tile_points = {u'a': 1, u'ą': 5, u'b': 3, u'c': 2, u'ć': 6, u'd': 2, u'e': 1, u'ę': 5, u'f': 5, u'g': 3,
                            u'h': 3, u'i': 1, u'j': 3, u'k': 2, u'l': 2, u'ł': 3, u'm': 2, u'n': 1, u'ń': 7, u'o': 1,
                            u'ó': 5, u'p': 2, u'r': 1, u's': 1, u'ś': 5, u't': 2, u'u': 3, u'w': 1, u'y': 2, u'z': 1,
                            u'ź': 9, u'ż': 5}
        self.tile_amounts = {u'a': 9, u'ą': 1, u'b': 2, u'c': 3, u'ć': 1, u'd': 3, u'e': 7, u'ę': 1, u'f': 1, u'g': 2,
                             u'h': 2, u'i': 8, u'j': 2, u'k': 3, u'l': 3, u'ł': 2, u'm': 3, u'n': 5, u'ń': 1, u'o': 6,
                             u'ó': 1, u'p': 3, u'r': 4, u's': 4, u'ś': 1, u't': 3, u'u': 2, u'w': 4, u'y': 4, u'z': 5,
                             u'ź': 1, u'ż': 1}

    def evaluate_max_points(self, word):
        if len(word) > 7 or len(word) < 1:
            return 0

        max_sum = 0
        starting_point = 0

        for x in range(7 - len(word), 7):

            current_sum = 0
            current_letters = {}

            for y in range(0, len(word)):
                letter = word[y]

                if letter in self.tile_points:
                    base_points = self.tile_points[letter]
                else:
                    base_points = 0

                if letter not in current_letters:
                    current_letters[letter] = 0

                current_letters[letter] += 1

                if (x+y) == 2 or (x+y) == 10:
                    letter_bonus_points = 2 * base_points
                else:
                    letter_bonus_points = base_points

                current_sum += letter_bonus_points

            for letter in current_letters:
                if letter not in self.tile_amounts or self.tile_amounts[letter] < current_letters[letter]:
                    return 0, 0

            word_bonus_points = 2 * current_sum

            if len(word) == 7:
                word_bonus_points += 50

            if word_bonus_points > max_sum:
                starting_point = x

            max_sum = max(max_sum, word_bonus_points)

        return max_sum, starting_point
