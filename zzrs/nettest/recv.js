const http = require('http');

const requestListener = function (request, response) {
  let body = [];
  request.on('data', (chunk) => {
    body.push(chunk);
  }).on('end', () => {
    response.writeHead(200);
    response.end(Date.now().toString());
  });
}

const server = http.createServer(requestListener);
server.listen(5600);