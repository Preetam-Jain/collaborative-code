import time
import sys

counter = 0

while True:
    sys.stdout.flush()
    print(f"Counter: {counter}")
    counter += 1
    time.sleep(1)

# import numpy as np
# import time

# # Define the size of the matrix
# matrix_size = 3000

# # Generate two large random matrices
# A = np.random.rand(matrix_size, matrix_size)
# B = np.random.rand(matrix_size, matrix_size)

# # Start timing the operation
# start_time = time.time()

# # Perform matrix multiplication
# C = np.dot(A, B)

# # Calculate eigenvalues
# eigenvalues = np.linalg.eigvals(C)

# # End timing the operation
# end_time = time.time()

# # Output the results
# print("Matrix multiplication and eigenvalue calculation completed.")
# print(f"Time taken: {end_time - start_time} seconds")
# print(f"First 10 eigenvalues: {eigenvalues[:10]}")