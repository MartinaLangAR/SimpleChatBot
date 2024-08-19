from ChatBot import ChatBot
from Dataset import Dataset
from nltk.tokenize import word_tokenize
import pickle

if __name__ == "__main__":

    dataset = Dataset(3)
    dataset.read_newfile("textresources/Sampletext.txt")
    bot = ChatBot(dataset)
    dataset.write_tofile()
    #bot.train("textresources/Sampletext.txt")
    #print(bot.answer_prompt("what is a"))
    #print(dataset.own_ngrams.keys())


