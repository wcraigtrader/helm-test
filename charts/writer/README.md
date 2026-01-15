# writer

Helm chart for the `writer` service.

## Overview

This chart deploys the `writer` Spring Boot service which publishes messages to RabbitMQ.

## RabbitMQ configuration

The chart exposes the following `rabbitmq` values (see `values.yaml`):

- `rabbitmq.host` - RabbitMQ hostname (default: `rabbitmq`)
- `rabbitmq.port` - RabbitMQ port (default: `5672`)
- `rabbitmq.username` - RabbitMQ username (default: `guest`)
- `rabbitmq.password` - RabbitMQ password (default: `guest`)
- `rabbitmq.queue` - Queue name (default: `messages`)

These are injected as environment variables into the container:

- `SPRING_RABBITMQ_HOST`
- `SPRING_RABBITMQ_PORT`
- `SPRING_RABBITMQ_USERNAME`
- `SPRING_RABBITMQ_PASSWORD`
- `RABBITMQ_QUEUE`

To override values at install time:

```bash
helm install writer charts/writer \
  --set image.repository=ghcr.io/your-org/writer \
  --set image.tag=v0.1.0 \
  --set rabbitmq.host=your-rabbit-host \
  --set rabbitmq.username=admin \
  --set rabbitmq.password=s3cr3t
```
