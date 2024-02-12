const http = require("http");
const apiServer = require('./api');
const { listen } = require("./sockets");
const io = require("socket.io");
const PORT = 3000;

const httpServer = http.createServer(apiServer);

httpServer.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});

const socketServer = io(httpServer);

listen(socketServer);