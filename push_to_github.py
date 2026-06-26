import os
from github import Github
from dotenv import load_dotenv

# Load token from .env
load_dotenv()
ACCESS_TOKEN = os.getenv("GITHUB_TOKEN")

# Connect to GitHub
g = Github(ACCESS_TOKEN)
repo = g.get_repo("virat216/leetcode_solutions")
# Example: Add a test file
file_path = "solutions/test_file.py"
commit_message = "Add test file"
file_content = "print('Hello from LeetCode automation!')"

repo.create_file(file_path, commit_message, file_content)
print("✅ Test file pushed to GitHub!")
