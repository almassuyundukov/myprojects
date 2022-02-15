package lesson11;

public class Matrix implements IMatrix {
    private double[][] numbers;

    public Matrix(int row, int col) {
        numbers = new double[row][col];
    }

    public Matrix(double[][] numbers) {
        this.numbers = numbers;
    }

    @Override
    public int getRows() {
        return numbers.length;
    }

    @Override
    public int getColumns() {
        return numbers[0].length;
    }

    @Override
    public double getValueAt(int rowIndex, int colIndex) throws IndexOutOfBoundsException {
        if (rowIndex < 0 || rowIndex >= getRows()) {
            System.out.println("Неверный индекс строки!");
            return -1;
        }

        if (colIndex < 0 || colIndex >= getColumns()) {
            System.out.println("Неверный индекс строки!");
            return -1;
        }

        return numbers[rowIndex][colIndex];
    }

    @Override
    public void setValueAt(int rowIndex, int colIndex, double value) throws IndexOutOfBoundsException {
        if (rowIndex < 0 || rowIndex >= getRows()) {
            System.out.println("Неверный индекс строки!");
            return;
        }

        if (colIndex < 0 || colIndex >= getColumns()) {
            System.out.println("Неверный индекс строки!");
            return;
        }

        numbers[rowIndex][colIndex] = value;
    }

    @Override
    public IMatrix add(IMatrix otherMatrix) throws IllegalArgumentException, NullPointerException {
        // this - первая матрица
        // otherMatrix - вторая матрица

        if (this.getColumns() != otherMatrix.getColumns()) {
            System.out.println("Не одинаковое количество колонок!");
            return null;
        }

        if (this.getRows() != otherMatrix.getRows()) {
            System.out.println("Не одинаковое количество строк!");
            return null;
        }

        Matrix matrix = new Matrix(this.getRows(), this.getColumns());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                matrix.setValueAt(i, j, this.getValueAt(i, j) + otherMatrix.getValueAt(i, j));
            }
        }

        return matrix;
    }

    @Override
    public IMatrix sub(IMatrix otherMatrix) throws IllegalArgumentException, NullPointerException {
        if (this.getColumns() != otherMatrix.getColumns()) {
            System.out.println("Не одинаковое количество колонок!");
            return null;
        }

        if (this.getRows() != otherMatrix.getRows()) {
            System.out.println("Не одинаковое количество строк!");
            return null;
        }

        Matrix matrix = new Matrix(this.getRows(), this.getColumns());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                matrix.setValueAt(i, j, this.getValueAt(i, j) - otherMatrix.getValueAt(i, j));
            }
        }

        return matrix;
    }

    @Override
    public IMatrix mul(IMatrix otherMatrix) throws IllegalArgumentException, NullPointerException {
        if (this.getColumns() != otherMatrix.getRows()) {
            System.out.println("Количество столбцов в первой матрице не совпадает с количеством строк во второй матрице");
            return null;
        }

        Matrix matrix = new Matrix(this.getRows(), otherMatrix.getColumns());
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < otherMatrix.getColumns(); j++){
                double index = 0;
                for(int n = 0; n < this.getColumns(); n++){
                    index = index + this.getValueAt(i,n) * otherMatrix.getValueAt(n,j);
                }
                matrix.setValueAt(i,j,index);
            }
        }
        return matrix;
    }

    @Override
    public IMatrix mul(double value) {
        Matrix matrix = new Matrix(this.getRows(), this.getColumns());
        for(int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                matrix.setValueAt(i,j,this.getValueAt(i,j)*value);
            }
        }
        return matrix;
    }

    @Override
    public IMatrix transpose() {
        Matrix matrix = new Matrix(this.getColumns(), this.getRows());
        for(int i = 0; i < this.getColumns(); i++) {
            for (int j = 0; j < this.getRows(); j++) {
                matrix.setValueAt(i,j,this.getValueAt(j,i));
            }
        }
        return matrix;
    }

    @Override
    public void fillMatrix(double value) {
        for(int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                this.setValueAt(i,j,value);
            }
        }
    }

    @Override
    public double determinant() {
        return 0;
    }

    @Override
    public boolean isNullMatrix() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (numbers[i][j] != 0) {
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public boolean isIdentityMatrix() {
        if(this.getRows() != this.getColumns()){
            System.out.println("Матрица не квадратная!");
        }
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j <this.getColumns(); j++){
                if(this.getValueAt(i,j) != 0 && i != j){
                    System.out.println("Матрица не является единичной");
                    return false;
                }
                if(this.getValueAt(i,i) != 1){
                    System.out.println("Матрица не является единичной");
                    return false;
            }
                }
        }
        return true;
    }

    @Override
    public boolean isSquareMatrix() {
        if(this.getRows() != this.getColumns()){
            return false;
        }
        return true;
    }

    @Override
    public void printToConsole() {
        for(int i = 0; i < this.getRows(); i++){
            for(int j = 0; j < this.getColumns(); j++){
                System.out.print(this.getValueAt(i,j) + "  ");
            }
            System.out.println();
        }
    }
}
