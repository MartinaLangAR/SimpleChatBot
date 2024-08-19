from nltk.tokenize import word_tokenize
from nltk import ngrams
from string import punctuation
import numpy as np
import pickle

class Dataset:
    def __init__(self, n: int):
        """Storing list of unique words and dictionaries to count occurence of ngram in trainingsset"""
        self.vocabulary = np.empty([0])
        self.own_ngrams = {}
        self.n = n


    def read_newfile(self, filename):
        stoplist = list(punctuation)

        f = open(filename, "r", encoding='UTF8')
        tokens = [token for token in word_tokenize(f.read()) if token not in stoplist and not token.isnumeric()]
        
        own_ngrams = list(ngrams(tokens, self.n))
        unique_ngrams = set(own_ngrams)
        self.own_ngrams = {elem: own_ngrams.count(elem) for elem in unique_ngrams}

        self.vocabulary = set(tokens)
    
    def write_tofile(self):
        filename = str(self.n) + "grams.pickle"
        with open(filename, 'wb') as handle:
            pickle.dump(self.own_ngrams, handle, protocol=pickle.HIGHEST_PROTOCOL)


    def load_fromfile(self, filename):
        with open(filename, 'r') as file:
            file.read(pickle.loads(self.own_ngrams)) 


