# Polynomial Solver (Java + JSON)

This project solves a quadratic polynomial of the form:

f(x) = ax² + bx + c

### Steps:
1. Reads input values from `input.json`.
2. Converts Y values stored in **base-n** into decimal.
3. Uses formula substitution & Cramer’s Rule to solve coefficients.
4. Prints **only the constant term `c`**.

---

## 🔧 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/PolynomialSolver.git
   cd PolynomialSolver
   ```

2. Compile the Java program:
   ```bash
   javac -cp .:json.jar src/PolynomialSolver.java
   ```

   > Note: You need `org.json` library. Download `json-20231013.jar` from [Maven repo](https://mvnrepository.com/artifact/org.json/json).

3. Run the program:
   ```bash
   java -cp .:json.jar src/PolynomialSolver
   ```

4. Output → Only prints **c** value.

---

## 📂 Example `input.json`
```json
{
  "base": 5,
  "xValues": [1, 2, 3],
  "yValues": ["132", "243", "144"]
}
```
