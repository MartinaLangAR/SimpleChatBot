from nltk.tokenize import word_tokenize
from Dataset import Dataset

class ChatBot:
    #ChatBot Class, that uses Dataset to answer simple prompts
    #method shoul either be 'avg' for average or 'max' for maximum
    def __init__(self, dataset: Dataset):
        self.dataset = dataset
        self.n = dataset.n
        self.vocabulary = dataset.vocabulary
        #self.tokenizer = RegexpTokenizer(r'\w+')

    def train(self, textfile):
        self.dataset.read_file(textfile)

    def prepend(self, partial_ngram):
        word_to_prepend = ""
        max_prob = 0
        for word in self.vocabulary:
            key_to_test = tuple([word] + partial_ngram)
            #print(f"Testing: {key_to_test}")
            if (key_to_test in self.dataset.own_ngrams.keys()):
                #print("Key is in Dataset")
                prob = self.dataset.own_ngrams[key_to_test]
                #print(f"prob is {prob}" )
                if prob > max_prob:
                    max_prob = prob
                    word_to_prepend = word
        if word_to_prepend == "":
            print (f"No matching word fount to prepend to \"{partial_ngram}\"")

        return word_to_prepend 
    
    def append(self, partial_ngram):
        word_to_append = ""
        max_prob = 0
        for word in self.vocabulary:
            key_to_test = tuple(partial_ngram + [word])
            #print(f"Testing: {key_to_test}")tart_index += 1 += 1tart_index += 1 += 1
            if (key_to_test in self.dataset.own_ngrams.keys()):
                #print("Key is in Dataset")
                prob = self.dataset.own_ngrams[key_to_test]
                #print(f"prob is {prob}" )
                if prob > max_prob:
                    max_prob = prob
                    word_to_append = word
        if word_to_append == "":
            print (f"No matching word found to append to \"{partial_ngram}\"")

        return word_to_append 


    def answer_prompt(self, prompt: str):
         """ Modify prompt in a way that makes it easier to use
           ngrams. Strategies:
            1 . replaces "What, who, Where" with word to test and return most probable one

         """
         if ( len(self.dataset.vocabulary) == 0 ):
             print ("Please call the train function with a text sample first")
             return None
         prompt_words = word_tokenize(prompt)
         if (prompt_words[0].lower() in ["what", "where", "who"]):
             if (prompt_words[-1] == "?"): prompt_words[-1] = "!"
             ##TODO: make sure prompt is not exceeded
             answer = [self.prepend( prompt_words[1:self.n])] + prompt_words[1:] #####!!!!!!!!!!!!!!!!!!!!!
         else:
            answer = "You want to know ".split(" ") + prompt_words
            start_index = len(answer) - (self.n -1)
            while (answer[-1] != "END" and start_index != 0):
                to_append = self.append(answer[start_index : min(start_index + self.n - 1, len(answer)) ])
                if ( to_append == ""):
                    start_index -=1
                    answer = answer[:-1]
                else:
                    answer.append(to_append)
                    start_index += 1
                if (len(answer) > 150):
                    answer.append( " and so on...")
                    break

         #print(answer)
         return " ".join(answer)