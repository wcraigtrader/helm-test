# helmtest (umbrella)

This umbrella chart installs the `reader` and `writer` subcharts.

Usage example:

```bash
helm dependency update charts/helmtest
helm install helmtest charts/helmtest \
  --set reader.image.repository=ghcr.io/your-org/reader \
  --set reader.image.tag=v0.1.0 \
  --set writer.image.repository=ghcr.io/your-org/writer \
  --set writer.image.tag=v0.1.0 \
  --set rabbitmq.host=your-rabbit-host
```

The umbrella chart uses local file dependencies to the `charts/reader` and `charts/writer` charts.
