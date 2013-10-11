from exception.empty_dictionary_exception import EmptyDictionaryException
from graph.dawg_node import DawgNode


class Dawg(object):
    def __init__(self, root=None):
        if root is None:
            self.root = DawgNode(None, None, None, True)
        else:
            self.root = root

        self.current_node = self.root
        self.previous_node = self.root

    def contains_word(self, word):
        self.__test_empty_dictionary()

        self.current_node = self.root
        self.previous_node = self.root

        return self.__recursive_contains_word(word)

    def __test_empty_dictionary(self):
        if self.root is None or self.root.letter is None:
            raise EmptyDictionaryException()

    def __recursive_contains_word(self, word):
        if self.current_node is None:
            return False

        if len(word) == 1 and self.current_node.letter == word and self.current_node.end_of_word:
            return True

        first_letter = word[:1]

        if first_letter == self.current_node.letter:
            self.previous_node = self.current_node
            self.current_node = self.current_node.child_node

            return self.__recursive_contains_word(word[1:])

        else:
            self.current_node = self.previous_node.next_node
            self.previous_node = self.current_node

            return self.__recursive_contains_word(word)
