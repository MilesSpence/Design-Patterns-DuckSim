# Design-Patterns-DuckSim

### The following are the instructions for the project:

### Overview

Open IntelliJ IDEA and create a new Kotlin project called DuckSim in your CS5704 directory. 

Setup for the DuckSim application.

On the next screen you are asked to choose the target JVM. Anything above 8 should work. I recommend using 11.

Once you have created your project, download the [kotlin-ducksim-starter](https://drive.google.com/file/d/1GbTEGdA8YTq7Kkk2uhqxbUKaVslh_rdn/view?usp=sharing) archive and unzip it. You should see a kotlin folder that contains the following:

    A ducksim folder with a lot of *.kt files in it
    A main.kt file

 Copy both of these into the kotlin folder in your project (found at src => main => kotlin).

Run the application. You should see a simple GUI that allows you to create different ducks. When you right-click on the ducks you should see a context menu. Experiment with the context menu to see what it does.

In this project, you are going to extend the functionality of the DuckSim application while using the design patterns, as described below. When you are finished, your application should behave similar to the one in this video:

In addition to the requirements given below, please implement the following two requirements.

    When you select a duck (left click on it), the background of the square should turn to the duck's color (instead of black)
    When a duck joins the welcoming committee, a little "w" should appear in the bottom left corner of the duck's square.

### Strategy Pattern

Use the Strategy Pattern to encapsulate flying behavior and quacking behavior. Create FlyBehavior and QuackBehavior interfaces. FlyBehavior should have 2 implementations: FlyNoWay and FlyWithWings. QuackBehavior should have 3 implementations: QuackNoWay, QuackNormal, and QuackSqueak. Implement the following capture and release behavior for ducks. When a duck is captured, it will neither fly nor quack. However, when a duck is released, it will exhibit the flying and quacking behavior that it did originally.
### Decoy Duck

Implement a decoy duck that can neither fly nor quack. The color of the decoy duck should be orange.
### Decorator Pattern

Add an abstract decorator class Bling to ducks. You should have 3 types of bling represented by the following classes: StarBling, MoonBling, and CrossBling. Each item of bling will be added to the display method by adding a colon and a * for star, a ) for moon, or a + for cross. For example, the display method of a mallard duck with two stars and a moon will return the string Mallard:\*:*:). The DuckSimView component is already configured to handle the bling.

The new MakeDuckDialog component (see above) already includes a bling panel in between the duck panel and the button panel, but it allows you to enter any negative bling numbers and it allows you to enter too many bling items. Update the code so that the bling numbers never go below 0, and the sum of all three bling numbers will never go above 3. Also, currently the bling is simply ignored and the duck is created. Use the factory pattern below to ensure the bling is appropriately added to a duck.
### Factory Pattern

Add class DuckFactory to the simulator. The duck factory should handle the creation of all ducks. It will have a function called createDuck with the following signature:

    createDuck(baseDuck: Duck, starCount: Int, moonCount: Int, crossCount: Int): Duck

The baseDuck argument should be not be a Bling type. Make the duck factory a singleton class!
### Adapter Pattern

Write an adapter called GooseDuck for the following Goose class:

    class Goose {
        val honkText = "Honk"
        val name = "Goose"
    }

The adapted goose should use the normal fly and quack strategies. However, the result of getHonk should appear when a goose “quacks” and the result of getName should appear on the goose’s button.
### Observer Pattern

Create your own Subject and Observer interfaces, and use those to implement the observer pattern as shown in HFDP. It is also fine to make Subject an abstract class, and implement methods registerObserver, removeObserver, and notifyObservers directly in the Subject class. However, Observer must be an interface, and it should have the update method.

The duck factory will be the subject and the ducks will be the potential observers. When a duck joins the DuckSim Welcoming Committee (DSWC) it registers with the duck factory so that when a new duck is created a Welcome message appears above the DSWC member’s name.

Modify the simulator so that if a duck is captured, it says Beware! instead of Welcome!.

The Welcome/Beware message should stay over the duck’s name until the screen is repainted.
### Composite Pattern

In this section, you will use the composite pattern to create a Flock class, which is the a composite for the Duck class. The requirements for the composite class are:

    The Flock must be a Duck
    The Flock will hold a collection of Ducks
    The Flock must display the word "Flock" followed by the first letter in the name of every Duck it holds (where the Bling would normally be)
    A Flock cannot have Bling
    The fly method works as usual on a Flock (it uses the Duck default)
    The quack method works as usual on a Flock, but it should the string "Noise!" instead of "Quack!"
    The joinDSCW method works as usual on a Flock, and every Duck that a Flock holds will also join the DSCW. Similarly with quitting the DSCW.
    The capture method works as usual on a Flock, and every Duck that a Flock holds will be captured. Similarly with releasing the Flock.

You will need to make some changes to the controller in order to effectively use this class. Change the code in DuckSimContoller that executes when the New-Duck button is pressed:

    when {
        view.newDuckButtonRectangle.contains(e.x, e.y) -> {
            NewDuckController(duckPond, view).createNewDuck()
        }
            

Then add a new class called NewDuckController that controls what happens when you click the "New Duck" button. The class should have fields "duckPond" and "view" to hold the model and the view, and a single method called createNewDuck. The createNewDuck method should create a NewDuckDialog when no ducks are selected (as was done originally), but when one or more ducks are selected, it should create a new Flock and add it to the model.

    fun createNewDuck() {
    
        if (duckPond.noSelectedDucks()) {
            val makeDuckDialog = MakeDuckDialog(duckPond, view)
            makeDuckDialog.setSize(300, 200)
            makeDuckDialog.isVisible = true
        } else {
            // get the selected ducks from the model
            // filter the selected ducks by removing any flocks
            // if there is more than one duck after removing flocks,
            // create a new flock with the selected ducks
            val flock = Flock(ducks)
            duckPond.addNewDuck(flock)
            view.repaint()
        }
    }

Essentially, the New-Duck button is used to create both Ducks and Flocks (composites), depending on whether any ducks are selected.
