# helm-test

As an Enterprise Java programmer, please write code for the following modules:

1) A Java 21 server process (writer) that publishes the current time, application version, and hostname environment variable in a JSON object to a RabbitMQ queue.
2) A Java 21 server process (reader) that reads a JSON object from a RabbitMQ queue and stores the last 100 objects in memory, and has an API to retrieve the JSON objects.
3) A single page web application running on nginx (port 8100) that queries the reader server and displays a summary of the JSON objects.

Each module needs to be packaged individually as a container that can be built using docker compose and published with Helm.
