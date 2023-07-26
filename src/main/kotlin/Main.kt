import java.lang.IllegalStateException

var x = ""

class FirstState : State {
    private var count = 0
    override val label: String = "First"

    override fun onStart(): State? {
        x += "a\n"
        return null
    }

    override fun onUpdate(): State? {
        x += count.toString() + "b\n"
        count++
        if (count < 3) {
            return null
        }
        return SecondState()
    }
}

class SecondState : State {
    override val label: String = "Second"

    override fun onStart(): State {
        x += "c\n"
        return FinishState()
    }

    override fun onUpdate() = throw IllegalStateException("Should never be here")
}

class FinishState : State {
    override val label: String = "Finished"

    override fun onStart(): State? {
        return null
    }

    override fun onUpdate(): State? {
        return null
    }
}

fun main(args: Array<String>) {
    val stateMachine = Statemachine(FirstState()) {
        currentState is FinishState
    }

    runCommandBlocking(stateMachine)

    println(x)
}