import socket


class Server:
    def __init__(self, host, port):
        self.socket = socket.socket()
        self.socket.bind((host, port))
        self.socket.listen(5)
        self.conn = None
        self.addr = None

    def establish_connection(self):
        self.conn, self.addr = self.socket.accept()
        print("Got a connection from", self.addr)
        return self.conn, self.addr
    
    def receive_msg(self):
        length_of_message = int.from_bytes(self.conn.recv(2), byteorder='big')
        msg = self.conn.recv(length_of_message).decode("UTF-8")
        return msg
    
    def send_msg(self, msg):
        msg_to_sent = msg.encode("UTF-8")
        self.conn.send(len(msg_to_sent).to_bytes(2, byteorder='big'))
        self.conn.send(msg_to_sent)

    def close_connection(self):
        pass

