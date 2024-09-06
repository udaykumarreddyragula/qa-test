 #!/bin/bash

# Define thresholds
CPU_LIMIT=80
MEMORY_LIMIT=80
DISK_LIMIT=80
#LOG_FILE="system_health.log"

# Function to log messages
#logger() {
# echo "$(date '+%Y-%m-%d %H:%M:%S') - $1" >> "$LOG_FILE"
#}
#Logger function not used as I have simply echoed the messages to console
# Check CPU usage
cpu_usage=$(top -bn1 | grep "Cpu(s)" | awk '{print $2 + $4}')
cpu_usage=${cpu_usage%.*}

if [ "$cpu_usage" -gt "$CPU_LIMIT" ]; then
  echo "$(date '+%Y-%m-%d %H:%M:%S') CPU limit exceeded: $cpu_usage%"
fi

# Check Memory usage
memory_usage=$(free | grep Mem | awk '{print $3/$2 * 100.0}')
memory_usage=${memory_usage%.*}
if [ "$memory_usage" -gt "$MEMORY_LIMIT" ]; then
  echo "$(date '+%Y-%m-%d %H:%M:%S') MEMORY limit exceeded: $memory_usage%"
fi

# Check Disk usage
disk_usage=$(df -h / | grep '/' | awk '{print $5}' | sed 's/%//g')
if [ "$disk_usage" -gt "$DISK_LIMIT" ]; then
  echo "$(date '+%Y-%m-%d %H:%M:%S') Disk limit exceed: $disk_usage%"
fi

# Check Running Processes
running_processes=$(ps aux | wc -l)
echo "$(date '+%Y-%m-%d %H:%M:%S') Total Running Processes: $running_processes"
