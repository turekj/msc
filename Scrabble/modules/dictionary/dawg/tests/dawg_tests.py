from unittest.case import TestCase
from dictionary.dawg import Dawg
from exception.empty_dictionary_exception import EmptyDictionaryException
from graph.dawg_node import DawgNode


class DawgTests(TestCase):
    def test_contains_word_throws_exception_on_empty_dictionary(self):
        dawg = Dawg()

        self.assertRaises(EmptyDictionaryException, dawg.contains_word, 'word')

    def test_contains_word_method(self):
        t_node = DawgNode('t', None, None, True)
        n_node = DawgNode('n', None, None, True)
        a_node = DawgNode('a', n_node, t_node, False)
        w_node = DawgNode('w', None, None, True)
        e_node = DawgNode('e', w_node, None, False)
        f_node = DawgNode('f', a_node, e_node, False)
        c_node = DawgNode('c', a_node, f_node, False)
        dawg = Dawg(c_node)

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
