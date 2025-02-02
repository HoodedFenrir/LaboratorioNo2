import java.util.*

class Evaluator {
    private val operators = setOf('+', '-', '*')

    fun evaluatePostfix(tokens: Queue<String>): Double {
        val stack = Stack<Double>()

        for (token in tokens) {
            when {
                token.isDouble() -> stack.push(token.toDouble())
                token.isOperator() -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(applyOperator(token[0], a, b))
                }
            }
        }
        return stack.pop()
    }

    private fun applyOperator(op: Char, a: Double, b: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            else -> throw IllegalArgumentException("Unknown operator: $op")
        }
    }

    private fun String.isDouble(): Boolean = this.toDoubleOrNull() != null
    private fun String.isOperator(): Boolean = this.length == 1 && this[0] in operators
}
