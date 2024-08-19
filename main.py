from ChatBot import ChatBot


if __name__ == "__main__":
    bot = ChatBot(2)
    #bot.train('textresources/Forster.txt')

    answer = bot.answer_prompt("What is missing?")
    print(answer)

    