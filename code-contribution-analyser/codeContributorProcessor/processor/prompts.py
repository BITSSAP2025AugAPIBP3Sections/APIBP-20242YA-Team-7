EXTRACT_CONTRIBUTORS_PROMPT = """
You are an expert software analyst. From the following GitHub contribution data, 
identify all contributors and list their key activities, areas of work, and technical domains.

Do not analyze, interpret, or summarize impact yet.

Output strictly in valid JSON using the following structure:

{{
  "contributors": [
    {{
      "name": "<contributor name>",
      "activities": ["<key activity or code area>", "..."]
    }}
  ]
}}

Critical formatting rules:
1. Do NOT include Markdown or code fences (no ```json or ```).
2. Do NOT include explanations, commentary, or any text outside the JSON object.
3. The response must begin with '{{' and end with '}}'.

Here is the full GitHub contribution data:
{repo_data}
"""

ANALYZE_CONTRIBUTOR_IMPACT_PROMPT = """
You are a senior software project reviewer. Given the following contributor activity data, 
evaluate each contributor's impact on project performance, maintainability, and innovation.

Assess each contributor based on technical depth, leadership, and overall influence. 
Do not use commit counts, line changes, or number of files modified as measures of contribution.

Output strictly in valid JSON format using the structure below:

{{
  "contributors": [
    {{
      "name": "<contributor name>",
      "impact_summary": "<summary of technical and strategic impact>"
    }}
  ]
}}

Critical formatting rules:
1. Do NOT include Markdown formatting or code fences (no ```json or ```).
2. Do NOT include commentary, explanations, or text outside the JSON object.
3. The response must begin with '{{' and end with '}}'.

Here is the contributor activity data:
{contributors_json}
"""

ASSIGN_PERCENTAGES_PROMPT = """
You are a software project evaluator. Given the following contributors and their impact summaries, 
assign effort-weighted contribution percentages based on relative influence, innovation, and importance.

Ensure that the total of all percentages equals 100.
Do not use commit counts, line additions, deletions, or any numerical activity metrics.

Output strictly in valid JSON format using the structure below:

{{
  "contributors": [
    {{
      "name": "<contributor name>",
      "contribution_percentage": <integer between 0 and 100>,
      "impact_summary": "<existing impact summary>"
    }}
  ]
}}

Critical formatting rules:
1. Do NOT include Markdown formatting or code fences (no ```json or ```).
2. Do NOT include explanations, commentary, or any text outside the JSON object.
3. The response must begin with '{{' and end with '}}'.

Here is the contributor impact data:
{impact_json}
"""

GENERATE_FINAL_SUMMARY_PROMPT = """
You are a lead software analyst. Given the following contributor-level data and raw project context, 
generate a final consolidated JSON summary capturing both individual and overall project impact.

The summary must follow this exact JSON structure:

{{
  "contributors": [
    {{
      "name": "<contributor name>",
      "contribution_percentage": <integer between 0 and 100>,
      "impact_summary": "<concise and meaningful description of contributor’s technical and strategic impact>"
    }}
  ],
  "overall_summary": "<holistic analysis of collaboration, innovation, and overall project quality>"
}}

Critical formatting rules:
1. Do NOT include Markdown formatting or code fences (no ```json or ```).
2. Do NOT include explanations, comments, or any text outside the JSON object.
3. The response must begin with '{{' and end with '}}'.

Here is the contributor data:
{contribution_json}

Here is the raw project data for context:
{repo_data}
"""
