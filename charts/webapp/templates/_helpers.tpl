{{/* Helper templates for webapp chart */}}
{{- define "webapp.name" -}}
{{- default .Chart.Name .Values.nameOverride -}}
{{- end -}}

{{- define "webapp.fullname" -}}
{{- printf "%s-%s" .Release.Name (include "webapp.name" .) | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "webapp.labels" -}}
app.kubernetes.io/name: {{ include "webapp.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/version: {{ .Chart.AppVersion }}
{{- end -}}
