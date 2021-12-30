package ducksim

import java.awt.Color

class GooseDuck(private val goose: Goose) : Duck() {
    override val color: Color = Color.BLUE
    override fun display() = getName()
    private fun getHonk(): String {
        return goose.honkText
    }

    private fun getName(): String {
        return goose.name
    }

    override val defaultQuackBehavior: QuackBehavior
        get() = QuackHonk()
}