package ducksim

import java.awt.Color

class Flock(val children: List<Duck>) : Duck() {
    override fun display(): String {
        var displayString = "Flock"
        children.forEach {
            displayString += when (it.color) {
                Color.BLUE -> ":G"
                Color.ORANGE -> ":D"
                Color.GREEN -> ":M"
                else -> ":R"
            }
        }
        return displayString
    }

    override val defaultQuackBehavior: QuackBehavior
        get() = QuackNoise()

    override val capture = object : DuckMenuItem {
        override fun invoke() {
            children.forEach {
                it.doCapture()
            }
            doCapture()
        }
    }

    override val release = object : DuckMenuItem {
        override fun invoke() {
            children.forEach {
                it.doRelease()
            }
            doRelease()
        }
    }

    override val joinDSCW = object : DuckMenuItem {
        override fun invoke() {
            children.forEach {
                it.doJoin()
            }
            doJoin()
        }
    }

    override val quitDSCW = object : DuckMenuItem {
        override fun invoke() {
            children.forEach {
                it.doQuit()
            }
            doQuit()
        }
    }
}