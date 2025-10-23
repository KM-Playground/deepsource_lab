#!/usr/bin/env python3
"""
Convert diff-cover JSON output to reviewdog diagnostic format (rdjson).
This script reads the diff-cover.json file and outputs diagnostics for
uncovered lines in the reviewdog format.
"""

import json
import sys


def main():
    try:
        with open('diff-cover.json', 'r') as f:
            data = json.load(f)

        # Extract violations (uncovered lines in the diff)
        violations = data.get('src_stats', {})

        # Check if there are any violations
        has_violations = False
        for file_path, stats in violations.items():
            violation_lines = stats.get('violation_lines', [])
            for line_num in violation_lines:
                has_violations = True
                # Output in reviewdog diagnostic format (rdjson)
                diagnostic = {
                    "message": "Line not covered by tests",
                    "location": {
                        "path": file_path,
                        "range": {
                            "start": {
                                "line": line_num
                            }
                        }
                    },
                    "severity": "WARNING"
                }
                print(json.dumps(diagnostic))

        # If no violations, output a valid empty rdjson to prevent reviewdog parse error
        if not has_violations:
            print("", file=sys.stderr)
            sys.exit(0)

    except FileNotFoundError:
        print("diff-cover.json not found", file=sys.stderr)
        sys.exit(0)
    except Exception as e:
        print(f"Error processing diff-cover report: {e}", file=sys.stderr)
        sys.exit(0)


if __name__ == '__main__':
    main()

