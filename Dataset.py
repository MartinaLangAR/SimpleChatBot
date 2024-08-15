from nltk.tokenize import word_tokenize
from nltk import ngrams
from string import punctuation
from nltk.corpus import stopwords
import numpy as np

class Dataset:
    def __init__(self):
        """Storing list of unique words and dictionaries to count occurence of ngram in trainingsset"""
        self.vocabulary = []
        self.bigrams = {}
        self.trigrams = {}
        self.fourgrams = {}

    def read_file(self, filename):
        stoplist = set(stopwords.words('english') + list(punctuation))

        f = open(filename, "r", encoding='UTF8')
        tokens = tokens = [token for token in word_tokenize(f.read()) if token not in stoplist and not token.isnumeric()]
        
        bigrams = list(ngrams(tokens, 2))
        unique_bigrams, count_bigrams = np.unique(bigrams, return_counts=True)
        self.bigrams = dict(zip(unique_bigrams, count_bigrams))
        
        trigrams = list(ngrams(tokens, 3))
        unique_trigrams, count_trigrams = np.unique(trigrams, return_counts=True)
        self.trigrams = dict(zip(unique_trigrams, count_trigrams))
        
        fourgrams = list(ngrams(tokens, 4))
        unique_fourgrams, count_fourgrams = np.unique(fourgrams, return_counts=True)
        self.fourgrams = dict(zip(unique_fourgrams, count_fourgrams))

        self.vocabulary = np.unique(np.asarray(tokens))



dataset = Dataset()
dataset.read_file("Forster.txt")
print(list(dataset.bigrams.values())[:150])
