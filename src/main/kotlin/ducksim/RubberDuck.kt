package ducksim

import java.awt.Color

class RubberDuck : Duck() {
    override val color: Color = Color.YELLOW
    override fun display() = "Rubber"
    override val defaultFlyBehavior: FlyBehavior
        get() = FlyNoWay()
    override val defaultQuackBehavior: QuackBehavior
        get() = QuackSqueak()
}