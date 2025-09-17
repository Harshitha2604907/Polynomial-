import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PolynomialSolver {
    
    // Function to convert base-n number to decimal
    public static int baseNtoDecimal(String num, int base) {
        return Integer.parseInt(num, base);
    }

    public static void main(String[] args) {
        try {
            // Step 1: Read JSON file (kept in project root)
            String content = new String(Files.readAllBytes(Paths.get("input.json")));
            JSONObject json = new JSONObject(content);

            // Example JSON structure:
            // { "base": 5, "xValues": [1,2,3], "yValues": ["132","243","144"] }
            int base = json.getInt("base");
            JSONArray yArray = json.getJSONArray("yValues");
            JSONArray xArray = json.getJSONArray("xValues");

            int n = yArray.length();
            int[] x = new int[n];
            int[] y = new int[n];

            // Decode base-n values
            for (int i = 0; i < n; i++) {
                x[i] = xArray.getInt(i);
                y[i] = baseNtoDecimal(yArray.getString(i), base);
            }

            // Step 2: Solve system of equations for quadratic f(x) = ax^2 + bx + c
            double[][] mat = {
                {Math.pow(x[0], 2), x[0], 1},
                {Math.pow(x[1], 2), x[1], 1},
                {Math.pow(x[2], 2), x[2], 1}
            };
            double[] rhs = {y[0], y[1], y[2]};

            // Solve using Cramer's Rule
            double det = determinant3x3(mat);
            double a = determinant3x3(new double[][] {
                {rhs[0], mat[0][1], mat[0][2]},
                {rhs[1], mat[1][1], mat[1][2]},
                {rhs[2], mat[2][1], mat[2][2]}
            }) / det;

            double b = determinant3x3(new double[][] {
                {mat[0][0], rhs[0], mat[0][2]},
                {mat[1][0], rhs[1], mat[1][2]},
                {mat[2][0], rhs[2], mat[2][2]}
            }) / det;

            double c = determinant3x3(new double[][] {
                {mat[0][0], mat[0][1], rhs[0]},
                {mat[1][0], mat[1][1], rhs[1]},
                {mat[2][0], mat[2][1], rhs[2]}
            }) / det;

            // Step 3: Print only c value
            System.out.println((int)c);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper function to calculate determinant of 3x3 matrix
    public static double determinant3x3(double[][] m) {
        return m[0][0]*(m[1][1]*m[2][2] - m[1][2]*m[2][1])
             - m[0][1]*(m[1][0]*m[2][2] - m[1][2]*m[2][0])
             + m[0][2]*(m[1][0]*m[2][1] - m[1][1]*m[2][0]);
    }
}
