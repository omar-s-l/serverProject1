# serverProject1

NOTES

Request and Response:

We decided to create two classes to simplify the handling of requests and responses.
The SimpleWebServer interprets a client request by creating a Request object with the data.
It then uses this Request object to construct a Response object.
The response object is created in such a way that its toString method will output the
response header as a string, which can then be sent to the client's output stream.
The Response object also stores the requested file (if applicable) as a byte[], which
can then be sent to the client's output stream as well (again, if applicable).

RedirectMap:

This class is a singleton that contains a HashMap of the redirect.defs file.
The client request paths are the keys of this HashMap, and the redirect URLs are the values.
If a client requests a path for a resource that has been moved, this HashMap is consulted
through the singleton to get the redirect path.