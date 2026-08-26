# AI Service Contract — Severity Classifier

## POST /classify-severity
Request:
{
  "incident_id": 123,
  "description": "string",
  "incident_type": "string"
}

Response (200):
{
  "severity": "LOW | MEDIUM | CRITICAL",
  "confidence": 0.0-1.0,
  "source": "ml_model",
  "model_version": "v1"
}

Response (503): model not loaded — Spring Boot falls back to rule-based logic.

## GET /health
Response: { "status": "ok", "model_loaded": true }

## Spring Boot behavior
- Timeout: 1.5-2s
- On timeout/error/non-200 → use internal rule-based classifier, tag source: "rule_based"
- Incident saved synchronously with final severity in a single write
