import locale
from exception.empty_dictionary_exception import EmptyDictionaryException
from exception.unsorted_words_exception import UnsortedWordsException
from graph.dawg_edge import DawgEdge
from graph.dawg_node import DawgNode


class Dawg(object):
    def __init__(self, root=None):
        if root is None:
            self.root = DawgNode()
        else:
            self.root = root

        self.previous_word = ''
        self.minimized = {}
        self.to_minimize = []

    def contains_word(self, word):
        current_node = self.root

        for letter in word:
            edge_has_letter = False

            for edge in current_node.edges:
                if edge.letter == letter:
                    current_node = edge.child_node
                    edge_has_letter = True
                    break

            if not edge_has_letter:
                return False

        return current_node.end_of_word

    def add_word(self, word):
        common_prefix_length = self._get_common_prefix_length(word, self.previous_word)
        self._minimize(common_prefix_length)

        if len(self.to_minimize) == 0:
            node = self.root
        else:
            node = self.to_minimize[-1].child_node

        for letter in word[common_prefix_length:]:
            next_node = DawgNode()
            edge_to_next_node = DawgEdge(letter, node, next_node)
            node.edges.append(edge_to_next_node)
            self.to_minimize.append(edge_to_next_node)
            node = next_node

        node.end_of_word = True
        self.previous_word = word

    @staticmethod
    def _get_common_prefix_length(word, previous_word):
        common_prefix_length = 0

        for x in range(0, min(len(word), len(previous_word))):
            if word[x] != previous_word[x]:
                return common_prefix_length

            common_prefix_length += 1

        return common_prefix_length

    def _minimize(self, minimize_to_index):
        for x in range(len(self.to_minimize) - 1, minimize_to_index - 1, -1):
            edge = self.to_minimize[x]

            if edge.child_node in self.minimized:
                edge.child_node = self.minimized[edge.child_node]
            else:
                self.minimized[edge.child_node] = edge.child_node

            self.to_minimize.pop()

    def minimize_remaining(self):
        self._minimize(0)

    def __str__(self):
        return self._get_edges_as_string_recursively(self.root)

    def _get_edges_as_string_recursively(self, node):
        edges_as_string = ''

        if node is None:
            return edges_as_string

        for edge in node.edges:
            edges_as_string += str(edge) + '\n'
            edges_as_string += self._get_edges_as_string_recursively(edge.child_node)

        return edges_as_string
