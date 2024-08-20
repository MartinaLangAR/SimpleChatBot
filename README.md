# SimpleChatBot

Naive Bayesian Text Generator and ChatBot

The System consists of two classes so far:

1. The Dataset class:

- Is initialized with an int n that defines the number of words per ngram.
- can either retrieve ngrams and vocbulary from a .txt file in UTF-8 format (_Note: Only English is supported for now_) or from a .pickle file
- Can write the obtained ngrams to a pickle file with automated filenaming
 Caution: If you load your ngrams from a file you have to make sure it fits for the chosen n. This is not checked and will lead to no output at all.

2. The Chatbot class:

- Needs to be initialized with a Dataset containing ngrams and a vocabulary.
- in case of an empty Dataset, you can use the train method of the ChatBot class to fill the dataset
- most importantly the answer_prompt function that takes a String as input and returns a string that may contain an answer if available with tha given dataset