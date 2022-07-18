import socket
import os


s = socket.socket()

port = int(os.getenv('PORT'))
db_name = str(os.getenv('DB_NAME'))
s.bind(('', port))
print("socket binded to %s" % (port))

s.listen(1)
print("socket is listening")

while True:
    c, addr = s.accept()
    print('Got connection from', addr)

    data = c.recv(1024)
    if not data:
        break
    response = ""
    action = data.decode()
    print('->'+str(action))
    if action == '#R#':
        f = open(db_name, "r")
        rows = f.readlines()
        print(rows)
        f.close()
        response = ''.join(rows)
    elif '#S#' in action:
        f = open(db_name, "a")
        f.write(action[3:] + '\n')
        f.close()
        response = '||SAVED||'
    else:
        response = '||INVALID_COMMAND||'

    print('<- '+str(response + "||END||"))
    c.send((response + "||END||").encode())
    c.close()
