{{- define "writer.name" -}}
{{- default .Chart.Name .Values.nameOverride -}}
{{- end -}}

{{- define "writer.fullname" -}}
{{- printf "%s-%s" (include "writer.name" .) .Release.Name -}}
{{- end -}}
