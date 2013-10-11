from exception.empty_dictionary_exception import EmptyDictionaryException
from exception.unsorted_words_exception import UnsortedWordsException
from graph.dawg_node import DawgNode


class Dawg(object):
    def __init__(self, root=None):
        if root is None:
            self.root = DawgNode(None, None, None, True)
        else:
            self.root = root

        self.current_node = self.root
        self.previous_node = self.root
        self.previous_word = ''
        self.minimized = {}
        self.to_minimize = []

    def contains_word(self, word):
        self._test_empty_dictionary()

        self.current_node = self.root
        self.previous_node = self.root

        return self._recursive_contains_word(word)

    def _test_empty_dictionary(self):
        if self.root is None or self.root.letter is None:
            raise EmptyDictionaryException()

    def _recursive_contains_word(self, word):
        if self.current_node is None:
            return False

        if len(word) == 1 and self.current_node.letter == word and self.current_node.end_of_word:
            return True

        first_letter = word[:1]

        if first_letter == self.current_node.letter:
            self.previous_node = self.current_node
            self.current_node = self.current_node.child_node

            return self._recursive_contains_word(word[1:])

        else:
            self.current_node = self.previous_node.next_node
            self.previous_node = self.current_node

            return self._recursive_contains_word(word)

    def add_word(self, word):
        if word < self.previous_word:
            raise UnsortedWordsException()

        common_prefix_length = self._get_common_prefix_length(self.previous_word, word)

        self._minimize(common_prefix_length)

        if len(self.to_minimize) == 0:
            node = self.root
        else:
            node = self.to_minimize[-1][1]

        for letter in word[common_prefix_length:]:
            next_node = DawgNode(letter=letter)

            if letter == word[common_prefix_length]:
                node.next_node = next_node
            else:
                node.child_node = next_node

            self.to_minimize.append((node, next_node))
            node = next_node

            if self.root.letter is None:
                self.root = node

        node.end_of_word = True
        self.previous_word = word

    def _get_common_prefix_length(self, previous_word, word):
        common_prefix_length = 0

        for x in range(0, min(len(previous_word), len(word))):
            if previous_word[x] != word[x]:
                return common_prefix_length

            common_prefix_length += 1

        return common_prefix_length

    def _minimize(self, to):
        for x in range(len(self.to_minimize) - 1, to - 1, -1):
            (parent, node) = self.to_minimize[x]

            if node in self.minimized:
                parent.child_node = self.minimized[node]
            else:
                self.minimized[node] = node

            self.to_minimize.pop()

    def minimize_remaining(self):
        self._minimize(0)
