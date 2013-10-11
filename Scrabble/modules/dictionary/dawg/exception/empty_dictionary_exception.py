class EmptyDictionaryException(Exception):
    def __init__(self):
        self.message = 'Dictionary is empty!'

    def __str__(self):
        return repr(self.message)
