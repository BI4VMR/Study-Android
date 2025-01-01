object MathUtil {
    fun devide(a: Int, b: Int): Int {
        if (b == 0) {
            throw IllegalArgumentException("Division by zero is not allowed")
        }
        return a / b
    }
}
