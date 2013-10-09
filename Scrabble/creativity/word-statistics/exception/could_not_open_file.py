
class CouldNotOpenFile(Exception):
    def __init__(self, filename):
        self.message = 'Could not open file: ' + filename

    def __str__(self):
        return repr(self.message)
