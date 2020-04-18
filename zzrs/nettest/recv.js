const http = require('http');
var fs = require('fs');

const requestListener = function (request, response) {
  let body = [];
  request.on('data', (chunk) => {
    body.push(chunk);
  }).on('end', () => {
    response.writeHead(200);

    var _body = Buffer.concat(body);
    var timeBefore = Date.now().toString();

    fs.writeFile("./data" + timeBefore + ".txt", _body, function (err) {
      if (err) {
        console.log(err);
      } else {
        console.log("DATA SAVED");
        response.end(JSON.stringify({
          "timeAfter": Date.now().toString(),
          "timeBefore": timeBefore
        }));
      }
    });
  });
}

const server = http.createServer(requestListener);
server.listen(5600);