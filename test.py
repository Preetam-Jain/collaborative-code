import time
import sys

counter = 0

while True:
    sys.stdout.flush()
    print(f"Counter: {counter}")
    counter += 1
    time.sleep(1)