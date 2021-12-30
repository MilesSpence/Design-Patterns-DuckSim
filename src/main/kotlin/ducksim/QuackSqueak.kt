package ducksim

class QuackSqueak : QuackBehavior {
    override val state = State.QUACKING
    override var quackText = "Squeak!"
}