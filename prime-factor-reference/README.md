# Reference Server

## Run

```sh
# install dependencies
npm install

# start server
npm start
```

## Example Request

```sh
>>>> http -v POST :3000 input:='[10,11]'
POST / HTTP/1.1
Accept: application/json, */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Content-Length: 19
Content-Type: application/json
Host: localhost:3000
User-Agent: HTTPie/1.0.2

{
    "input": [
        10,
        11
    ]
}

HTTP/1.1 200 OK
Connection: keep-alive
Content-Length: 24
Content-Type: application/json; charset=utf-8
Date: Thu, 12 Sep 2019 14:40:53 GMT
ETag: W/"18-9dNYlyvsBkzMxa0cdPVboRXrlMo"
X-Powered-By: Express

[
    {
        "factors": [
            2,
            5
        ],
        "number": 10
    },
    {
        "factors": [
            11
        ],
        "number": 11
    }
]
```

## Request Format

```json
{
  "input": [
    10,
    11
  ]
}
```

## Response Format

```json
[
    {
        "factors": [
            2,
            5
        ],
        "number": 10
    },
    {
        "factors": [
            11
        ],
        "number": 11
    }
]
```