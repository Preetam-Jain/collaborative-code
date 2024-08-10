from fastapi import FastAPI
from pydantic import BaseModel
import os
import uuid

class CodeSubmission(BaseModel):
    code: str

app = FastAPI()

@app.post("/execute")
async def execute_code(submission: CodeSubmission):
    script_id = str(uuid.uuid4())
    file_name = f"/tmp/{script_id}.py"

    print(submission.code)
    return {'status': 'successful'}
    # with open(file_name, 'w') as script:
    #     script.write(submission.code)
    
