from unittest.case import TestCase
from dictionary.dawg import Dawg
from exception.empty_dictionary_exception import EmptyDictionaryException
from exception.unsorted_words_exception import UnsortedWordsException
from graph.dawg_edge import DawgEdge
from graph.dawg_node import DawgNode


class DawgTests(TestCase):
    def test_contains_word_method(self):
        root = DawgNode()
        c_child = DawgNode()
        a_child = DawgNode()
        n_child = DawgNode(end_of_word=True)
        t_child = DawgNode(end_of_word=True)
        f_child = DawgNode()
        e_child = DawgNode()
        w_child = DawgNode(end_of_word=True)

        root.edges.append(DawgEdge('c', root, c_child))
        root.edges.append(DawgEdge('f', root, f_child))
        c_child.edges.append(DawgEdge('a', c_child, a_child))
        a_child.edges.append(DawgEdge('n', a_child, n_child))
        a_child.edges.append(DawgEdge('t', a_child, t_child))
        f_child.edges.append(DawgEdge('e', f_child, e_child))
        f_child.edges.append(DawgEdge('a', f_child, a_child))
        e_child.edges.append(DawgEdge('w', e_child, w_child))

        dawg = Dawg(root)

        contains_cat = dawg.contains_word('cat')
        contains_can = dawg.contains_word('can')
        contains_fat = dawg.contains_word('fat')
        contains_fan = dawg.contains_word('fan')
        contains_few = dawg.contains_word('few')
        contains_cant = dawg.contains_word('cant')
        contains_fant = dawg.contains_word('fant')
        contains_ca = dawg.contains_word('ca')

        self.assertTrue(contains_cat)
        self.assertTrue(contains_can)
        self.assertTrue(contains_fat)
        self.assertTrue(contains_fan)
        self.assertTrue(contains_few)
        self.assertFalse(contains_cant)
        self.assertFalse(contains_fant)
        self.assertFalse(contains_ca)

    def test_add_word_method(self):
        dawg = Dawg()
        dawg.add_word('can')
        dawg.add_word('cat')
        dawg.add_word('fan')
        dawg.add_word('few')
        dawg.minimize_remaining()

        contains_can = dawg.contains_word('can')
        contains_cat = dawg.contains_word('cat')
        contains_fan = dawg.contains_word('fan')
        contains_fat = dawg.contains_word('fat')
        contains_few = dawg.contains_word('few')

        self.assertTrue(contains_can)
        self.assertTrue(contains_cat)
        self.assertTrue(contains_fan)
        self.assertFalse(contains_fat)
        self.assertTrue(contains_few)
