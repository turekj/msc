class WordImportFailed(Exception):
    def __init__(self, word):
        self.message = 'Imported word: ' + word + ' was not found in dictionary'

    def __str__(self):
        return repr(self.message)

