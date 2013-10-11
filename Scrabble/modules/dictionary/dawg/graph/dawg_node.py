
class DawgNode(object):
    NodeId = 0

    def __init__(self, letter=None, child_node=None, next_node=None, end_of_word=False):
        self.id = DawgNode.NodeId
        DawgNode.NodeId += 1
        self.letter = letter
        self.child_node = child_node
        self.next_node = next_node
        self.end_of_word = end_of_word

    def __str__(self):
        string_value = ''

        if self.child_node is not None:
            string_value += str(self.child_node.id) + '/'

        if self.next_node is not None:
            string_value += str(self.next_node.id) + '/'

        if self.letter is not None:
            string_value += str(self.letter) + '/'

        if self.end_of_word:
            string_value += '1'
        else:
            string_value += '0'

        return string_value

    def __eq__(self, other):
        return str(self) == str(other)

    def __hash__(self):
        return str(self).__hash__()
