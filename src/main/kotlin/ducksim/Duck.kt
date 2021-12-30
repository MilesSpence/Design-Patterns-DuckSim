package ducksim

import java.awt.Color

abstract class Duck : Observer {
    // values that can be overridden
    open val defaultFlyBehavior: FlyBehavior = FlyWithWings()
    open val defaultQuackBehavior: QuackBehavior = QuackNormal()
    open val color: Color = Color.BLACK

    // variables that can be changed only by functions in this class
    var flyBehavior: FlyBehavior = defaultFlyBehavior
        protected set
    var quackBehavior: QuackBehavior = defaultQuackBehavior
        protected set
    var state = State.SWIMMING
        protected set
    var isFree = true
        protected set
    var isOnDSWC = false
        private set
    var quackText = quackBehavior.quackText
    var holdFlyBehavior: FlyBehavior = flyBehavior
    var holdQuackBehavior: QuackBehavior = quackBehavior

    // function for setting the state back to its default (swimming)
    open fun swim() {
        state = State.SWIMMING
    }

    // functions from the context menu
    open fun fly() {
        state = flyBehavior.state
    }

    open fun quack() {
        state = quackBehavior.state
    }

    fun doJoin() {
        isOnDSWC = true
        DuckFactory.registerObserver(this)
    }

    open val joinDSCW = object : DuckMenuItem {
        override fun invoke() {
            doJoin()
        }
    }

    fun doQuit() {
        isOnDSWC = false
        DuckFactory.removeObserver(this)
    }

    open val quitDSCW = object : DuckMenuItem {
        override fun invoke() {
            doQuit()
        }
    }

    fun doCapture() {
        isFree = false
        flyBehavior = FlyNoWay()
        quackBehavior = QuackNoWay()
    }

    open val capture = object : DuckMenuItem {
        override fun invoke() {
            doCapture()
        }
    }

    fun doRelease() {
        isFree = true
        flyBehavior = holdFlyBehavior
        quackBehavior = holdQuackBehavior
    }

    open val release = object : DuckMenuItem {
        override fun invoke() {
            doRelease()
        }
    }

    // abstract display function that must be implemented by concrete classes
    abstract fun display(): String

    override fun update() {
        state = State.WELCOMING
    }
}