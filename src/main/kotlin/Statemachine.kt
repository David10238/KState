interface State {
    val label: String
    fun onStart(): State?
    fun onUpdate(): State?
}

class Statemachine(startingState: State, private val finishedCondition: Statemachine.() -> Boolean) : Command {
    constructor(startingState: State) : this(startingState, { false })

    var currentState = startingState
        private set


    private fun startLoop() {
        while (true) {
            val state = currentState.onStart() ?: break
            currentState = state
        }
    }

    override fun onStart() {
        startLoop()
    }

    override fun onUpdate() {
        currentState = currentState.onUpdate() ?: return
        startLoop()
    }

    override fun isFinished(): Boolean {
        return finishedCondition()
    }

    override fun onStop() {
    }
}
