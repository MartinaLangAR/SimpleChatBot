# SimpleChatBot

Naive Bayesian Text Generator and ChatBot

To run the project you need to run start_backend.py (this will block your terminal and output debugging messages) and ChatGUI.java in a separate one. As long as you have ale dependecies installed this should open a GUI on your desktop and you are free to Chat with Boty. 

The System consists of three classes in Python and two in Java so far:

###Python

1. The Dataset class:

- Is initialized with an int n that defines the number of words per ngram.
- can either retrieve ngrams and vocbulary from a .txt file in UTF-8 format (_Note: Only English is supported for now_) or from a .pickle file
- Can write the obtained ngrams to a pickle file with automated filenaming
 Caution: If you load your ngrams from a file you have to make sure it fits for the chosen n. This is not checked and will lead to no output at all.

2. The Chatbot class:

- Needs to be initialized with a Dataset containing ngrams and a vocabulary.
- in case of an empty Dataset, you can use the train method of the ChatBot class to fill the dataset
- most importantly the answer_prompt function that takes a String as input and returns a string that may contain an answer if available with tha given dataset

3. The Server class
Provides a socket for sending and receiving messages from a client. So for this is hardcoded to a local host, but should be easily adapted to a webserver edition.


###JAVA:

1.Client.java
Deals with the message exchange to a background python server that runs the Chatbot logic itself.

2.ChatGUI.java
Paints the GUI and calls functions from the client class to send and receive data.

