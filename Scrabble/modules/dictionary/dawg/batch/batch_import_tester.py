# -*- encoding: utf-8 -*-
from dictionary.dawg import Dawg
from exception.word_import_failed import WordImportFailed

class BatchImportTester(object):
    @staticmethod
    def test_import(filename):
        word_count = 0
        with open(filename, 'r') as file_handle:
            dawg = Dawg()

            for word in file_handle:
                dawg.add_word(word.decode('UTF-8').strip())
                word_count += 1

                if word_count % 10000 == 0:
                    print 'Imported ' + str(word_count) + ' words'

            file_handle.seek(0)
            word_count = 0

            for word in file_handle:
                if not dawg.contains_word(word.decode('UTF-8').strip()):
                    raise WordImportFailed(word.decode('UTF-8').strip())

                word_count += 1

                if word_count % 10000 == 0:
                    print 'Tested ' + str(word_count) + ' words are imported'
