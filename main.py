from ChatBot import ChatBot
from NGramScraper import NGramScraper

if __name__ == "__main__":
    bot = ChatBot
    scraper = NGramScraper()
    result = scraper.retrieve_data("Albert Einstein")
    print(result)

    