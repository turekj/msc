# -*- encoding: utf-8 -*-
from collections import OrderedDict
import timeit
from evaluation.best_characters_evaluator import BestCharactersEvaluator
from evaluation.opening_points_evaluator import OpeningPointsEvaluator
from exception.could_not_open_file import CouldNotOpenFile


class ScrabbleStatisticsProcessor(object):
    def __init__(self):
        self.openings_evaluator = OpeningPointsEvaluator()
        self.best_letters_evaluator = BestCharactersEvaluator()
        self._start_time = None
        self._end_time = None
        self._evaluated_scores = {}

    def process_file(self, filename_to_read):
        self._get_start_time()

        try:
            with open(filename_to_read, 'r') as dictionary:
                for word in dictionary:
                    word = word.strip()

                    self._evaluated_scores[word] = self.openings_evaluator.evaluate_max_points(word.decode('UTF-8'))
                    self.best_letters_evaluator.evaluate_best_characters(word)

        except IOError:
            raise CouldNotOpenFile(filename_to_read)

        self._get_end_time()
        self._print_summary()

    def _get_start_time(self):
        self._start_time = timeit.default_timer()

    def _get_end_time(self):
        self._end_time = timeit.default_timer()

    def _print_summary(self):
        print 'Time elapsed: ' + str(self._end_time - self._start_time)

        sorted_openings = OrderedDict(sorted(self._evaluated_scores.items(), key=lambda x: x[1], reverse=True))

        print 'Best openings: '

        i = 0

        for (word, (points, starting_point)) in sorted_openings.iteritems():
            if i == 10:
                break

            print str(i + 1) + '. ' + word + ' = ' + str(points) + ' starting at: ' + str(starting_point)
            i += 1

        sorted_best_characters = OrderedDict(
            filter(lambda x: len(x[0]) == 7,
                   sorted(self.best_letters_evaluator.get_evaluation_results().items(), key=lambda x: x[1],
                          reverse=True)))

        print 'Best letters: '

        i = 0

        for letters, score in sorted_best_characters.iteritems():
            if i == 10:
                break

            print str(i + 1) + '. ' + letters + ' = ' + str(score)
            i += 1
