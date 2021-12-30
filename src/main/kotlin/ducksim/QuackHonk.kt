package ducksim

class QuackHonk : QuackBehavior {
    override val state = State.QUACKING
    override val quackText = "Honk"
}