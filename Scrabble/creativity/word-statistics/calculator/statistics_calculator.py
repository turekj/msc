
class StatisticsCalculator(object):

    __words = 0
    __letters = 0
    __letter_frequency = {}

    def __init__(self, words):
        self.__process_words(words)

    def __process_words(self, words):
        for word in words:
            self.__increment_word_count()
            self.__append_letter_probability_data(word)

    def __increment_word_count(self):
        self.__words += 1

    def __append_letter_probability_data(self, word):
        self.__letters += len(word)

        for letter in word:
            if letter not in self.__letter_frequency:
                self.__letter_frequency[letter] = 0

            self.__letter_frequency[letter] += 1

    def get_words_count(self):
        return self.__words

    def get_letter_probability(self, letter):
        letter_count = 0

        if letter in self.__letter_frequency:
            letter_count = self.__letter_frequency[letter]

        return letter_count / self.__letters
