from calculator.statistics_calculator import StatisticsCalculator
from exception.could_not_open_file import CouldNotOpenFile


class StatisticsProcessor:
    calculator = StatisticsCalculator()

    def process_file(self, filename):
        try:
            with open(filename, 'r') as dictionary:
                for word in dictionary:
                    self.calculator.process_word(word)
        except IOError:
            raise CouldNotOpenFile(filename)

        self.__print_summary()

    def __print_summary(self):
        print "summary"
