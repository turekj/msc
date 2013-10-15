from validation.word_validator import WordValidator


class BestCharactersEvaluator(object):
    def __init__(self):
        self.best_characters = {}
        self.word_validator = WordValidator()

    def evaluate_best_characters(self, word):
        if not self.word_validator.is_word_valid(word):
            return

        sorted_letters = sorted(list(word))
        sorted_letters_as_string = ''.join(sorted_letters)

        if sorted_letters_as_string not in self.best_characters:
            self.best_characters[sorted_letters_as_string] = 0

        self.best_characters[sorted_letters_as_string] += 1

    def get_evaluation_results(self):
        return self.best_characters
