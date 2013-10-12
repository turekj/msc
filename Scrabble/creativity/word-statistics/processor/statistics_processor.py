# -*- encoding: utf-8 -*-
import timeit
from calculator.statistics_calculator import StatisticsCalculator
from exception.could_not_open_file import CouldNotOpenFile


class StatisticsProcessor:
    calculator = StatisticsCalculator()
    __read_words = 0

    def __init__(self):
        self.__start = None
        self.__end = None

    def process_file(self, filename_to_read, filename_to_write):
        try:
            with open(filename_to_write, 'w') as log_file:

                self.__get_start_time()

                try:
                    with open(filename_to_read, 'r') as dictionary:
                        for word in dictionary:
                            self.calculator.process_word(word.decode('UTF-8'))
                except IOError:
                    raise CouldNotOpenFile(filename_to_read)

                self.__get_end_time()
                self.__print_summary(log_file)

        except IOError:
            raise CouldNotOpenFile(filename_to_write)

    def __get_start_time(self):
        self.__start = timeit.default_timer()

    def __get_end_time(self):
        self.__end = timeit.default_timer()

    def __print_summary(self, file_to_write):
        self.__write_to_file(file_to_write, 'Summary: ')
        self.__write_to_file(file_to_write, 'Seconds elapsed: ' + str(self.__end - self.__start))
        self.__write_to_file(file_to_write, 'Words processed: ' + str(self.calculator.get_words_count()))
        self.__write_to_file(file_to_write, 'Letter probability: ')
        self.__print_letter_probability_summary(file_to_write)
        self.__print_ngram_count_summary(file_to_write, 2, 10)
        self.__print_ngram_count_summary(file_to_write, 3, 10)
        self.__print_ngram_count_summary(file_to_write, 4, 10)
        self.__print_ngram_count_summary(file_to_write, 5, 10)
        self.__print_ngram_count_summary(file_to_write, 6, 10)
        self.__print_ngram_count_summary(file_to_write, 7, 10)

    def __print_letter_probability_summary(self, file_to_write):
        letter_count = self.calculator.get_most_frequent_letters()
        self.__write_to_file(file_to_write, '\t' + 'Letter' + '\t=>\t' + 'Count[Probability]')

        for letter in letter_count:
            self.__write_to_file(file_to_write, '\t' + letter + '\t=>\t' + str(letter_count[letter]) + '[' + str(
                self.calculator.get_letter_probability(letter)) + ']')

    def __print_ngram_count_summary(self, file_to_write, ngram_length, records_to_print):
        most_probable_ngrams = self.calculator.get_most_probable_ngrams(ngram_length)
        self.__write_to_file(file_to_write, '\t' + str(ngram_length) + '-gram\t=>\t' + 'Count')

        for x in range(0, records_to_print):
            ngram_entry = most_probable_ngrams.items()[x]

            self.__write_to_file(file_to_write, '\t' + ngram_entry[0] + '\t=>\t' + str(ngram_entry[1]))

    def __write_to_file(self, file_to_write, to_write):
        print >> file_to_write, to_write.encode('UTF-8')
