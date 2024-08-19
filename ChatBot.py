from Dataset import Dataset
from nltk.tokenize import RegexpTokenizer


class ChatBot:
    #ChatBot Class, that uses NGramScraper to answer simple prompts
    #method shoul either be 'avg' for average or 'max' for maximum
    def __init__(self, n: int):
        self.dataset = Dataset(n)
        self.vocabulary = self.dataset.vocabulary
        self.n = n
        self.tokenizer = RegexpTokenizer(r'\w+')

    def train(self, textfile):
        self.dataset.read_file(textfile)

    def prepend(self, partial_ngram):
        word_to_prepend = ""
        max_prob = 0
        for word in (self.vocabulary): 
            prob = self.dataset.own_ngrams[tuple( [word] + partial_ngram)]
            if prob > max_prob:
                max_prob = prob
                word_to_prepend = word
        return word_to_prepend 


    def answer_prompt(self, prompt: str):
         """ Modify prompt in a way that makes it easier to use
           ngrams. Strategies:
            1 . replaces "What, who, Where" with word to test and return most probable one

         """
         if ( self.dataset.vocabulary == None):
             print ("Please call the train function with a text sample first")
             return None
         prompt_words = self.tokenizer.tokenize(prompt)
         if (prompt_words[0].lower() in ["what", "where", "who"]):
             ##TODO: make sure prompt is not exceeded
             word_to_prepend = self.prepend( prompt_words[1:self.n]) #####!!!!!!!!!!!!!!!!!!!!!


         return word_to_prepend