class UnsortedWordsException(Exception):
    def __init__(self):
        self.message = 'Words should be sorted alphabetically!'

    def __str__(self):
        return repr(self.message)

