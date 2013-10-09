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
        print "Summary: "
        print "Seconds elapsed: " + str(self.__end - self.__start)

