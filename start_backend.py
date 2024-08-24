from ChatBot import ChatBot
from Dataset import Dataset
from server import Server

if __name__ == "__main__":

    dataset = Dataset(3)
    dataset.load_fromfile("textresources/Forster_3grams.pickle")
    bot = ChatBot(dataset)

    serv = Server("localhost", 2004)
    serv.establish_connection()

    while True:
        answer = bot.answer_prompt(serv.receive_msg())
        serv.send_msg(answer)


