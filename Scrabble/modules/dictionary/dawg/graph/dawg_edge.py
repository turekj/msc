class DawgEdge(object):
    def __init__(self, letter=None, parent_node=None, child_node=None):
        self.letter = letter
        self.parent_node = parent_node
        self.child_node = child_node

    def __str__(self):
        return 'Edge: ' + self.letter + ' from: ' + str(self.parent_node.id) + ' to: ' + str(self.child_node.id)
