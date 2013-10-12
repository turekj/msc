
class DawgNode(object):
    NodeId = 0

    def __init__(self, edges=None, end_of_word=False):
        self.id = DawgNode.NodeId
        self.end_of_word = end_of_word

        if edges is None:
            self.edges = []
        else:
            self.edges = edges

        self._increment_node_id()

    def _increment_node_id(self):
        DawgNode.NodeId += 1

    def __str__(self):
        string_value = ''

        for edge in self.edges:
            string_value += edge.letter + '/'
            string_value += str(edge.child_node.id) + '/'

        if self.end_of_word:
            string_value += '1'
        else:
            string_value += '0'

        return string_value

    def __eq__(self, other):
        return str(self) == str(other)

    def __hash__(self):
        return repr(self).__hash__()
