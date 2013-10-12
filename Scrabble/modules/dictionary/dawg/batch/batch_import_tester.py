# -*- encoding: utf-8 -*-
from dictionary.dawg import Dawg
from exception.word_import_failed import WordImportFailed


class BatchImportTester(object):
    @staticmethod
    def test_import(filename):
        with open(filename, 'r') as file_handle:
            dawg = Dawg()

            for word in file_handle:
                dawg.add_word(word.strip())

            file_handle.seek(0)

            for word in file_handle:
                if not dawg.contains_word(word.strip()):
                    raise WordImportFailed(word.strip())
