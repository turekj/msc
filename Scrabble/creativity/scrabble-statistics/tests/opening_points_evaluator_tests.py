# coding=utf-8
from unittest.case import TestCase
from evaluation.opening_points_evaluator import OpeningPointsEvaluator


class OpeningPointsEvaluatorTests(TestCase):
    def test_evaluate_max_points_method(self):
        evaluator = OpeningPointsEvaluator()

        poznmyz_points = evaluator.evaluate_max_points(u'późńmyż')

        self.assertEqual((132, 0), poznmyz_points)
