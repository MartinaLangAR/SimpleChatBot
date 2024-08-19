from nltk.tokenize import word_tokenize
from nltk import ngrams
from string import punctuation
from nltk.corpus import stopwords
import numpy as np

class Dataset:
    def __init__(self, n: int):
        """Storing list of unique words and dictionaries to count occurence of ngram in trainingsset"""
        self.vocabulary = None
        self.own_ngrams = {}
        self.n = n


    def read_file(self, filename):
        stoplist = set(stopwords.words('english') + list(punctuation))

        f = open(filename, "r", encoding='UTF8')
        tokens = tokens = [token for token in word_tokenize(f.read()) if token not in stoplist and not token.isnumeric()]
        
        own_ngrams = list(ngrams(tokens, self.n))
        unique_ngrams, count_ngrams = np.unique(own_ngrams, return_counts=True)
        self.own_ngrams = dict(zip(unique_ngrams, count_ngrams))
        

        self.vocabulary = np.unique(np.asarray(tokens))


