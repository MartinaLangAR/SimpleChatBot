from NGramScraper import NGramScraper
from nltk.tokenize import RegexpTokenizer


class ChatBot:
    #ChatBot Class, that uses NGramScraper to answer simple prompts
    #method shoul either be 'avg' for average or 'max' for maximum
    def __init__(self, method = 'max', start_year: int = 2000, end_year: int = 2015, corpus: int = 26, smoothing: int = 0):
        self.scraper = NGramScraper(start_year, end_year, corpus, smoothing)
        self.method = method
        #self.vocabulary = brown.words()
        self.tokenizer = RegexpTokenizer(r'\w+')

    def prepend(self, partial_ngram):
        word_to_prepend = ""
        max_prob = 0
        for word in (self.vocabulary + "_START_"):
            prob = 0
            
            probs = self.scraper.retrieve_data(word + " " + partial_ngram)
            if probs == None:
                continue
            else:
                if (self.method == 'max'):
                    #work only with maximum amount of apperances
                    prob = max(probs[0])
                else:
                    #average would be to divide by amount of datapoints, but this is equal
                    # for every datapoint and should therefore be omitted to prevent underflow
                    prob = sum(probs[0])
                if prob > max_prob:
                    max_prob = prob
                    word_to_prepend = word
        return word_to_prepend 


    def answer_prompt(self, prompt: str):
         """ Modify prompt in a way that makes it easier to use
           ngrams. Strategies:
            1 . replaces "What, who, Where" with word to test and return most probable one

            Retrieve Start and End:  
         _START_ signals the start of a sentence whereas _END_ the end respectivly
         """
         prompt_words = self.tokenizer.tokenize(prompt)
         if (prompt_words[0].lower() in ["what", "where", "who"]):
             word_to_prepend = self.prepend( " ".join(prompt_words[1:])) #####!!!!!!!!!!!!!!!!!!!!!


         return word_to_prepend