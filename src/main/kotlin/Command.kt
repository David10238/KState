fun runCommandBlocking(command: Command) {
    command.onStart()
    command.onUpdate()
    while (!command.isFinished()) {
        command.onUpdate()
    }
    command.onStop()
}

interface Command {
    fun onStart()
    fun onUpdate()
    fun isFinished(): Boolean
    fun onStop()
}