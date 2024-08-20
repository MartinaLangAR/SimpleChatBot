from nltk.tokenize import word_tokenize
from nltk import ngrams
from string import punctuation
import numpy as np
import pickle
import os.path

class Dataset:
    def __init__(self, n: int):
        """Storing list of unique words and dictionaries to count occurence of ngram in trainingsset"""
        self.vocabulary = [] #np.empty([0])
        self.own_ngrams = {}
        self.n = n
        self.filename = ""


    def __repr__(self):
        return f"Dataset with stored {self.n}grams obtained from {self.filename}.txt"
    
    def print_ngrams(self):
        i = 0
        print("ngrams:")
        for key, value in self.own_ngrams.items():
            print (f"{key} : {value}")
            i+=1
            if (i > 100):
                print ("...")
                break

    #@staticmethod
    def retrieve_vocabulary(self):
        for ngram in self.own_ngrams:
            for word in ngram:
                self.vocabulary.append(word)
        self.vocabulary = set(self.vocabulary)

    @staticmethod
    def update_dict(dict1, dict2):
        keys = list(set(list(dict1.keys()) + list(dict2.keys())))
        vals = [ dict1.get(key, 0) + dict2.get(key, 0) for key in keys]
        return dict(zip(keys, vals))

    def read_newfile(self, filename):
        stoplist = list(punctuation) + ["\""]
        self.filename = filename.split(".")[0]

        f = open(filename, "r", encoding='UTF8')
        processed_string = f.read().replace(".", "<END> <START>" )
        tokens = [token for token in word_tokenize(processed_string) if token not in stoplist and not token.isnumeric()]
        
        own_ngrams = list(ngrams(tokens, self.n))
        unique_ngrams = set(own_ngrams)
        new_ngrams = {elem: own_ngrams.count(elem) for elem in unique_ngrams}
        self.own_ngrams = self.update_dict(self.own_ngrams, new_ngrams)

        self.vocabulary = set(tokens)
    
    def write_tofile(self):
        filename = self.filename + "_" + str(self.n) + "grams0.pickle"
        while (os.path.isfile(filename)):
            filename = filename[:-8] + str(int(filename[-8]) + 1) + ".pickle"
        with open(filename, 'wb') as file:
            pickle.dump(self.own_ngrams, file)


    def load_fromfile(self, filename):
        new_ngrams = {}
        file = open(filename, 'rb')
        new_ngrams = pickle.load(file)
        file.close() 
        self.own_ngrams = self.update_dict(self.own_ngrams, new_ngrams)
        self.retrieve_vocabulary()


