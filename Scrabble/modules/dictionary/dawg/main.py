import argparse
from batch.batch_import_tester import BatchImportTester


def main():
    parser = argparse.ArgumentParser(
        description='Tests reading dictionary to a DAWG structure')
    parser.add_argument('file', help='relative path to file')
    arguments = parser.parse_args()

    BatchImportTester.test_import(arguments.file)

if __name__ == "__main__":
    main()

