package ducksim

import java.awt.Color

class DecoyDuck : Duck() {
    override val color: Color = Color.ORANGE
    override fun display() = "Decoy"
    override val defaultFlyBehavior: FlyBehavior
        get() = FlyNoWay()
    override val defaultQuackBehavior: QuackBehavior
        get() = QuackNoWay()
}