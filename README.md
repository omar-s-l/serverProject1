# serverProject1

NOTES

RedirectMap:

This class is a singleton that contains a HashMap of the redirect.defs file.
The client request paths are the keys of this HashMap, and the redirect URLs are the values.
If a client requests a path for a resource that has been moved, this HashMap is consulted
through the singleton to get the redirect path.