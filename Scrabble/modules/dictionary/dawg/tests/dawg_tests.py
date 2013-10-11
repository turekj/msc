from unittest.case import TestCase
from dictionary.dawg import Dawg
from exception.empty_dictionary_exception import EmptyDictionaryException
from exception.unsorted_words_exception import UnsortedWordsException
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

    def test_add_word_throws_exception_on_adding_unsorted_words(self):
        dawg = Dawg()

        dawg.add_word('this')
        dawg.add_word('will')

        self.assertRaises(UnsortedWordsException, dawg.add_word, 'fail')

    def test_add_word_method(self):
        dawg = Dawg()

        dawg.add_word('add')
        dawg.add_word('me')
        dawg.minimize_remaining()

        root_node = dawg.root

        self.assertIsNotNone(root_node.child_node)
        first_child_of_root = dawg.root.child_node

        self.assertIsNotNone(first_child_of_root.child_node)
        first_child_of_first_child_of_root = first_child_of_root.child_node

        self.assertIsNotNone(root_node.next_node)
        next_node_of_root = dawg.root.next_node

        self.assertIsNotNone(next_node_of_root.child_node)
        first_child_of_next_node_of_root = next_node_of_root.child_node

        self.assertEqual('a', root_node.letter)
        self.assertEqual(False, root_node.end_of_word)

        self.assertEqual('d', first_child_of_root.letter)
        self.assertFalse(first_child_of_root.end_of_word)
        self.assertIsNone(first_child_of_root.next_node)

        self.assertEqual('d', first_child_of_first_child_of_root.letter)
        self.assertTrue(first_child_of_first_child_of_root.end_of_word)
        self.assertIsNone(first_child_of_first_child_of_root.child_node)
        self.assertIsNone(first_child_of_first_child_of_root.next_node)

        self.assertEqual('m', next_node_of_root.letter)
        self.assertFalse(next_node_of_root.end_of_word)
        self.assertIsNone(next_node_of_root.next_node)

        self.assertEqual('e', first_child_of_next_node_of_root.letter)
        self.assertTrue(first_child_of_next_node_of_root.end_of_word)
        self.assertIsNone(first_child_of_next_node_of_root.child_node)
        self.assertIsNone(first_child_of_next_node_of_root.next_node)
