#!/usr/bin/env bash
set -euo pipefail

SAMPLES_DIR="samples"
MAIN_CLASS="SimpleLang"

# Directory containing SimpleLang.class
CLASSPATH="out/production/SimpleLang:utilities/antlr-4.13.1-complete.jar:utilities/jackson-databind-2.17.0.jar:utilities/jackson-core-2.17.0.jar:utilities/jackson-annotations-2.17.0.jar"


# Use Java 21 explicitly (path from your update-alternatives output)
JAVA_CMD="/usr/lib/jvm/java-21-openjdk-amd64/bin/java"

for in_file in "$SAMPLES_DIR"/sample*.sl; do
  # skip the *-out.sl files (they are expected outputs, not inputs)
  [[ "$in_file" == *"-out.sl" ]] && continue

  base="$(basename "$in_file" .sl)"

  exp1="$SAMPLES_DIR/${base}-out.sl"
  exp2="$SAMPLES_DIR/${base}-out"

  if [[ -f "$exp1" ]]; then
    expected="$exp1"
  elif [[ -f "$exp2" ]]; then
    expected="$exp2"
  else
    echo "⚠️  No expected file for $in_file, skipping"
    continue
  fi

  echo "▶ Running $base"

  tmp_out="$(mktemp)"
  "$JAVA_CMD" -cp "$CLASSPATH" "$MAIN_CLASS" "$in_file" > "$tmp_out"

  if diff -u "$expected" "$tmp_out"; then
    echo "✅ $base OK"
  else
    echo "❌ $base DIFFERS"
  fi

  rm -f "$tmp_out"
  echo
done
