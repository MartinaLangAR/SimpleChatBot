from ChatBot import ChatBot
from Dataset import Dataset

if __name__ == "__main__":

    dataset = Dataset(3)
    #dataset.read_newfile("textresources/Forster.txt")
    #dataset.read_newfile("textresources/Sampletext2.txt")
    #dataset.read_newfile("textresources/Sampletext.txt")
    dataset.load_fromfile("textresources/Forster_3grams.pickle")
    #dataset.write_tofile()
    dataset.print_ngrams()
    bot = ChatBot(dataset)

    
    print(bot.answer_prompt("How?"))



