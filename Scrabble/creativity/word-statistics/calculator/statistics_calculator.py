from collections import OrderedDict


class StatisticsCalculator(object):

    __words = 0
    __letters = 0
    __letter_frequency = {}
    __ngrams = {}

    def __init__(self):
        self.__words = 0
        self.__letters = 0
        self.__letter_frequency = {}
        self.__ngrams = {}

    def process_words(self, words):
        for word in words:
            self.process_word(word)

    def process_word(self, word):
        stripped_word = word.strip()
        self.__increment_word_count()
        self.__append_letter_probability_data(stripped_word)
        self.__process_ngram_data(stripped_word)

    def __increment_word_count(self):
        self.__words += 1

    def __append_letter_probability_data(self, word):
        self.__letters += len(word)

        for letter in word:
            if letter not in self.__letter_frequency:
                self.__letter_frequency[letter] = 0

            self.__letter_frequency[letter] += 1

    def __process_ngram_data(self, word):
        for ngram_length in range(1, len(word) + 1):
            for ngram_offset in range(0, len(word) + 1 - ngram_length):
                ngram = word[ngram_offset:ngram_offset + ngram_length]

                if ngram not in self.__ngrams:
                    self.__ngrams[ngram] = 0

                self.__ngrams[ngram] += 1

    def get_words_count(self):
        return self.__words

    def get_letter_probability(self, letter):
        letter_count = 0

        if letter in self.__letter_frequency:
            letter_count = self.__letter_frequency[letter]

        return float(letter_count) / float(self.__letters)

    def get_most_frequent_letters(self):
        return OrderedDict(sorted(self.__letter_frequency.items(), key=lambda x: x[1], reverse=True))

    def get_ngram_count(self, ngram):
        ngram_count = 0

        if ngram in self.__ngrams:
            ngram_count = self.__ngrams[ngram]

        return ngram_count

    def get_most_probable_ngrams(self, ngram_length):
        filtered_ngrams = {x:self.__ngrams[x] for x in self.__ngrams if len(x) == ngram_length}

        return OrderedDict(sorted(filtered_ngrams.items(), key=lambda x: x[1], reverse=True))
