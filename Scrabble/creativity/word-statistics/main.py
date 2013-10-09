import argparse
from processor.statistics_processor import StatisticsProcessor


def main():
    parser = argparse.ArgumentParser(
        description='Reads dictionary from file and prints various statistics for the word set')
    parser.add_argument('file', help='relative path to file')
    parser.add_argument('output_file', help='relative path to output file')
    arguments = parser.parse_args()

    StatisticsProcessor().process_file(arguments.file, arguments.output_file)

if __name__ == "__main__":
    main()
