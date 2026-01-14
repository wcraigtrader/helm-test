{{- define "reader.name" -}}
{{- default .Chart.Name .Values.nameOverride -}}
{{- end -}}

{{- define "reader.fullname" -}}
{{- printf "%s-%s" (include "reader.name" .) .Release.Name -}}
{{- end -}}
