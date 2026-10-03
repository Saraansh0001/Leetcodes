class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {

        int oldRows = mat.length;
        int oldCols = mat[0].length;

        if (oldRows * oldCols != r * c) {
            return mat;
        }

        int[][] result = new int[r][c];

        int row = 0;
        int col = 0;

        for (int i = 0; i < oldRows; i++) {
            for (int j = 0; j < oldCols; j++) {

                result[row][col] = mat[i][j];

                col++;

                if (col == c) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }
}