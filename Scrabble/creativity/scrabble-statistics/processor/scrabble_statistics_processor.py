# -*- encoding: utf-8 -*-
from collections import OrderedDict
import timeit
from evaluation.opening_points_evaluator import OpeningPointsEvaluator
from exception.could_not_open_file import CouldNotOpenFile


class ScrabbleStatisticsProcessor(object):
    def __init__(self):
        self.evaluator = OpeningPointsEvaluator()
        self._start_time = None
        self._end_time = None
        self._evaluated_scores = {}

    def process_file(self, filename_to_read):
        self._get_start_time()

        try:
            with open(filename_to_read, 'r') as dictionary:
                for word in dictionary:
                    word = word.strip()

                    self._evaluated_scores[word] = self.evaluator.evaluate_max_points(word.decode('UTF-8'))

        except IOError:
            raise CouldNotOpenFile(filename_to_read)

        self._get_end_time()
        self._print_summary()

    def _get_start_time(self):
        self._start_time = timeit.default_timer()

    def _get_end_time(self):
        self._end_time = timeit.default_timer()

    def _print_summary(self):
        sorted_openings = OrderedDict(sorted(self._evaluated_scores.items(), key=lambda x: x[1], reverse=True))

        i = 0
        print 'Time elapsed: ' + str(self._end_time - self._start_time)

        for (word, (points, starting_point)) in sorted_openings.items():
            if i == 10:
                break

            print str(i + 1) + '. ' + word + ' = ' + str(points) + ' starting at: ' + str(starting_point)
            i += 1
