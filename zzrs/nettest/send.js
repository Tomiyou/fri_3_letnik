const request = require('request');
const fs = require('fs');

// dd if=/dev/zero of=file.txt count=1024 bs=1024

var id = 0;
var interval = parseInt(process.argv[2]);

function send() {
  var ts = Date.now();
  fs.createReadStream('dummyfile').pipe(
    request
      .put('http://13.81.58.48:5600')
      .on('response', function (res) {
        res.body = "";
        res.on('data', function (chunk) {
          res.body += chunk;
        });
        res.on('end', function () {
          var response = parseInt(res.body);
          console.log("Difference time:", id, response - ts);
          id += 1;
        });
      })
      .on('error', function (err) {
        console.error(err)
      })
  );
}

send();
setInterval(send, interval);
