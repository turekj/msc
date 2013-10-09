import timeit
from calculator.statistics_calculator import StatisticsCalculator
from exception.could_not_open_file import CouldNotOpenFile


class StatisticsProcessor:
    calculator = StatisticsCalculator()
    __read_words = 0

    def process_file(self, filename):
        self.__get_start_time()

        try:
            with open(filename, 'r') as dictionary:
                for word in dictionary:
                    self.__handle_interaction()
                    self.calculator.process_word(word)
        except IOError:
            raise CouldNotOpenFile(filename)

        self.__get_end_time()
        self.__print_summary()

    def __get_start_time(self):
        self.__start = timeit.default_timer()

    def __handle_interaction(self):
        self.__read_words += 1

        if self.__read_words % 1000 == 0:
            print "Processed " + str(self.__read_words) + " words..."

    def __get_end_time(self):
        self.__end = timeit.default_timer()

    def __print_summary(self):
        print 'Summary: '
        print 'Seconds elapsed: ' + str(self.__end - self.__start)
        print 'Words processed: ' + str(self.calculator.get_words_count())
        print 'Letter probability: '
        self.__print_letter_probability_summary()

    def __print_letter_probability_summary(self):
        letter_count = self.calculator.get_most_frequent_letters()
        print '\t' + 'Letter' + '\t=>\t' + 'Count[Probability]'

        for letter in letter_count:
            print '\t' + letter + '\t=>\t' + str(letter_count[letter]) + '[' + str(
                self.calculator.get_letter_probability(letter)) + ']'
