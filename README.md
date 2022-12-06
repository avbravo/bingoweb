
VIDEO
VICTOR OROZCO
https://www.youtube.com/watch?v=SzxLMRPfNBU

# Health
/health/ready: Returns the result of all readiness checks and determines whether or not your application can process requests
/health/live: Returns the result of all liveness checks and determines whether or not your application is up- and running
/health : As in previous versions of MicroProfile Health there was no distinction between readiness and liveness, this is active for downwards compatibility. This endpoint returns the result of both health check types.

curl -v http://localhost:8080/health/live


## Metrics
https://dzone.com/articles/eclipse-microprofile-metrics-practical-use-cases
    
Base: Mandatory Metrics for all MicroProfile implementations, located at /metrics/base
Application: Custom metrics exposed by the developer, located at /metrics/application
Vendor: Any MicroProfile implementation could implement its own variant, located at /metrics/vendor

http://localhost:8080/metrics
http://localhost:8080/metrics/base

curl -H "Accept: application/json" http://localhost:8080/metrics/base