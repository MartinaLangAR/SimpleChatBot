import socket
from ChatBot import ChatBot
from Dataset import Dataset


#setup server connection to client
soc = socket.socket()
host = "localhost"
port = 2004
soc.bind((host, port))
soc.listen(5)

#setup ChatBot and Dataset
dset = Dataset(n=3)
dset.load_fromfile("textresources/Forster_3grams.pickle")
chat = ChatBot(dataset=dset)


while True:
    conn, addr = soc.accept()
    print("Got connection from",addr)
    length_of_message = int.from_bytes(conn.recv(2), byteorder='big')
    msg = conn.recv(length_of_message).decode("UTF-8")
    print(f"Server received {msg}")
    answer = chat.answer_prompt(msg)
    

    # If we got a new prompt
    if  msg:
        message_to_send = answer.encode("UTF-8")
        conn.send(len(message_to_send).to_bytes(2, byteorder='big'))
        conn.send(message_to_send)
    else:
        print("no new prompt to answer")


