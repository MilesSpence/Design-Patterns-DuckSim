package ducksim

abstract class Bling(val duck: Duck) : Duck() {
    init {
        flyBehavior = duck.flyBehavior
        quackBehavior = duck.quackBehavior
        holdFlyBehavior = duck.flyBehavior
        holdQuackBehavior = duck.quackBehavior
    }

    override val color = duck.color

    override fun display(): String {
        return duck.display()
    }

    override fun quack() {
        state = quackBehavior.state
        quackText = quackBehavior.quackText
    }

    override val release = object : DuckMenuItem {
        override fun invoke() {
            isFree = true
            flyBehavior = holdFlyBehavior
            quackBehavior = holdQuackBehavior
            quackText = duck.quackText
        }
    }
}