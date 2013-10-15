from unittest.case import TestCase
from evaluation.best_characters_evaluator import BestCharactersEvaluator


class BestCharactersEvaluatorTests(TestCase):
    def test_evaluate_best_characters_method(self):
        evaluator = BestCharactersEvaluator()

        evaluator.evaluate_best_characters('meats')
        evaluator.evaluate_best_characters('steam')
        evaluator.evaluate_best_characters('mates')
        evaluator.evaluate_best_characters('ward')
        evaluator.evaluate_best_characters('draw')
        evaluator.evaluate_best_characters('cry')
        evaluator.evaluate_best_characters('carry')

        results = evaluator.get_evaluation_results()

        self.assertIn('aemst', results)
        self.assertIn('adrw', results)
        self.assertIn('cry', results)
        self.assertIn('acrry', results)
        self.assertEqual(3, results['aemst'])
        self.assertEqual(2, results['adrw'])
        self.assertEqual(1, results['cry'])
        self.assertEqual(1, results['acrry'])
