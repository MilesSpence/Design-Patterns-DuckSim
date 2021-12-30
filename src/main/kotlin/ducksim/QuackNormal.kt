package ducksim

class QuackNormal : QuackBehavior {
    override val state = State.QUACKING
    override val quackText = "Quack!"
}