# coding=utf-8
class WordValidator(object):
    def __init__(self):
        self.tile_amounts = {u'a': 9, u'ą': 1, u'b': 2, u'c': 3, u'ć': 1, u'd': 3, u'e': 7, u'ę': 1, u'f': 1, u'g': 2,
                             u'h': 2, u'i': 8, u'j': 2, u'k': 3, u'l': 3, u'ł': 2, u'm': 3, u'n': 5, u'ń': 1, u'o': 6,
                             u'ó': 1, u'p': 3, u'r': 4, u's': 4, u'ś': 1, u't': 3, u'u': 2, u'w': 4, u'y': 4, u'z': 5,
                             u'ź': 1, u'ż': 1}

    def is_word_valid(self, word):
        letter_amounts = {}

        for letter in word:
            if letter not in letter_amounts:
                letter_amounts[letter] = 0

            letter_amounts[letter] += 1

        for letter in letter_amounts:
            if letter not in self.tile_amounts or letter_amounts[letter] > self.tile_amounts[letter]:
                return False

        return True
