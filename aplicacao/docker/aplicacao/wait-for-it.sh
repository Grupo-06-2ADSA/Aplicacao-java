#!/usr/bin/env bash
# wait-for-it.sh

set -e

TIMEOUT=30
HOST=$(echo $1 | cut -d : -f 1)
PORT=$(echo $1 | cut -d : -f 2)

until (echo > /dev/tcp/$HOST/$PORT) >/dev/null 2>&1; do
  echo "Waiting for $HOST:$PORT..."
  sleep 1
done

exec "${@:2}"