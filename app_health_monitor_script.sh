#!/bin/bash

# URL of the application to check
APP_URL="http://localhost:80"
LOG_FILE="app_health.log"

# Function to log messages
log_message() {
  echo "$(date '+%Y-%m-%d %H:%M:%S') - $1" >> "$LOG_FILE"
}

# Check Application Health
response=$(curl -o /dev/null -s -w "%{http_code}" "$APP_URL")



if [ "$response" -eq 200 ]; then
  log_message "Application is UP. Getting status code: $response"
else
  log_message "Application is DOWN. Received status code: $response"
fi
