#!/usr/bin/env python3
"""
Merge multiple diff-cover JSON reports into a single report.
This script reads all diff-cover-*.json files and combines them into one diff-cover.json file.
"""

import json
import glob
import sys


def main():
    merged = {
        "report_name": "XML",
        "diff_name": "PR diff",
        "src_stats": {},
        "total_num_lines": 0,
        "total_num_violations": 0,
        "total_percent_covered": 0,
        "num_changed_lines": 0
    }

    report_files = glob.glob("diff-cover-*.json")
    
    if not report_files:
        print("No diff-cover reports found to merge", file=sys.stderr)
        sys.exit(0)

    for report_file in report_files:
        try:
            with open(report_file, 'r') as f:
                data = json.load(f)
                merged["src_stats"].update(data.get("src_stats", {}))
                merged["total_num_lines"] += data.get("total_num_lines", 0)
                merged["total_num_violations"] += data.get("total_num_violations", 0)
                merged["num_changed_lines"] += data.get("num_changed_lines", 0)
        except Exception as e:
            print(f"Error reading {report_file}: {e}", file=sys.stderr)
            continue

    # Recalculate total percent covered
    if merged["total_num_lines"] > 0:
        covered_lines = merged["total_num_lines"] - merged["total_num_violations"]
        merged["total_percent_covered"] = (covered_lines / merged["total_num_lines"]) * 100
    else:
        merged["total_percent_covered"] = 100

    with open("diff-cover.json", 'w') as f:
        json.dump(merged, f, indent=2)

    print(f"Merged {len(report_files)} reports into diff-cover.json")


if __name__ == '__main__':
    main()

