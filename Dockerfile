FROM python:3

WORKDIR /app

COPY test.py /app

CMD ["python3", "./test.py"]