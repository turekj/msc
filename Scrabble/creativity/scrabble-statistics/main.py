import argparse
from processor.scrabble_statistics_processor import ScrabbleStatisticsProcessor


def main():
    parser = argparse.ArgumentParser(
        description='Reads dictionary from file and prints various statistics for the word set')
    parser.add_argument('file', help='relative path to file')
    arguments = parser.parse_args()

    ScrabbleStatisticsProcessor().process_file(arguments.file)

if __name__ == "__main__":
    main()
