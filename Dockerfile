FROM python:3

WORKDIR /app

RUN pip3 install fastapi
RUN pip3 install pydantic

COPY python_executor.py /app

CMD ["fastapi", "dev", "./python_executor.py"]