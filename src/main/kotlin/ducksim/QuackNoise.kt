package ducksim

class QuackNoise : QuackBehavior {
    override val state = State.QUACKING
    override val quackText = "Noise!"
}