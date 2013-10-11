
class DawgNode(object):
    def __init__(self):
        self.letter = None
        self.child_node = None
        self.next_node = None
        self.end_of_word = False

    def __init__(self, letter, child_node, next_node, end_of_word):
        self.letter = letter
        self.child_node = child_node
        self.next_node = next_node
        self.end_of_word = end_of_word
