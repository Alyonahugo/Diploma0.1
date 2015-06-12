package schulze;

/**
 * Provides scalable square matrix with default operations of storing/retrieving
 * integer values. The size of matrix depends on the amount of registered
 * candidates. It is assumed that there won't be any polls with the number of
 * candidates larger than MAX_INT.</br> The most frequent operation for this
 * matrix is incrementing some cell. Next is adding row&column for new
 * candidate. The least used one is removing the candidate and thus deleting the
 * column and row.
 * 
 * @author 
 * 
 */
class Matrix {
	private static int DEFAULT_INITIAL_SIZE = 16;
	private long[][] matrix;
	private int maximumSize;
	private int actualSize = 0;

	public Matrix() {
		this(DEFAULT_INITIAL_SIZE);
	}

	public Matrix(int initialSize) {
		this.matrix = new long[initialSize][initialSize];
		this.maximumSize = initialSize;
		this.actualSize = 0;
	}

	private void checkBounds(int x, int y) {
		if (x > actualSize - 1 || y > actualSize - 1) {
			throw new IndexOutOfBoundsException(String.format("Accessing element [%d][%d] in matrix of size [%d][%d]", x, y,
			    actualSize, actualSize));
		}
	}

	public long get(int x, int y) {
		checkBounds(x, y);
		return matrix[x][y];
	}

	public void set(int x, int y, int value) {
		checkBounds(x, y);
		matrix[x][y] = value;
	}

	public void inc(int x, int y) {
		checkBounds(x, y);
		matrix[x][y]++;
	}

	public void dec(int x, int y) {
		checkBounds(x, y);
		matrix[x][y]--;
	}

	/**
	 * Increases matrix size if it has reached its maximum size
	 */
	public void handleCandidateAdded() {
		if (actualSize >= maximumSize) {
			int doubleMaximumSize = maximumSize * 2;
			long[][] doubleMatrix = new long[doubleMaximumSize][doubleMaximumSize];
			for (int i = 0; i < maximumSize; i++) {
				System.arraycopy(matrix[i], 0, doubleMatrix[i], 0, maximumSize);
			}
			matrix = doubleMatrix;
			maximumSize = doubleMaximumSize;
		}
		actualSize++;
	}
}
