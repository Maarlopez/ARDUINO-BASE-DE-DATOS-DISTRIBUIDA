import socket
import sys

# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect the socket to the port where the server is listening
server_address = ('10.20.1.200', 8000)
print('connecting to {} port {}'.format(*server_address))
sock.connect(server_address)

try:

    # Send data
    while 1:
        print("CMD ->:")
        message = str(input()).encode()
        print('sending {!r}'.format(message))
        sock.sendall(message)

        _response = ""
        while True:
            data = sock.recv(64)
            _response = _response + data.decode("utf-8")
            if '||END||' in _response:
                break
        print(_response)

finally:
    print('closing socket')
    sock.close()
