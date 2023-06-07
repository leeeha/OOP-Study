package util

class Validator {
    fun checkNullOrEmpty(input: String?) {
        if (input == null) {
            throw NullPointerException(NULL_INPUT_ERROR)
        }

        if (input.trim().isEmpty()) {
            throw IllegalArgumentException(EMPTY_INPUT_ERROR)
        }
    }

    fun checkSpace(values: List<String>) {
        for (e in values) {
            if (e.trim().isEmpty()) throw IllegalArgumentException(INVALID_SPACE_ERROR)
        }
    }

    fun checkOperator(str: String) {
        // 4개의 연산자 중 하나와 일치하면 바로 리턴
        for (value in Operator.values()) {
            if (value.op == str) return
        }
        throw IllegalArgumentException(INVALID_OPERATOR_ERROR)
    }

    fun checkFormulas(values: List<String>) {
        // 문자열의 시작과 끝이 숫자가 아닌 경우
        checkStartEndPoint(values)

        // 숫자나 문자가 연달아 입력된 경우
        checkContinuousType(values)
    }

    private fun checkStartEndPoint(values: List<String>) {
        if (!isNumeric(values[0]) || !isNumeric(values.last()))
            throw IllegalArgumentException(INVALID_FORMULAS_ERROR)
    }

    private fun checkContinuousType(values: List<String>) {
        val types = mutableListOf<Type>()

        for (str in values) {
            if (isNumeric(str)) {
                if (types.isNotEmpty() && types.last() == Type.NUMBER) {
                    throw IllegalArgumentException(CONTINUOUS_NUMBER_ERROR)
                }
                types.add(Type.NUMBER)
            } else {
                if (types.isNotEmpty() && types.last() == Type.OPERATOR) {
                    throw IllegalArgumentException(CONTINUOUS_OPERATOR_ERROR)
                }
                types.add(Type.OPERATOR)
            }
        }
    }

    private fun isNumeric(str: String): Boolean {
        return str.all { ch -> ch.isDigit() }
    }
}