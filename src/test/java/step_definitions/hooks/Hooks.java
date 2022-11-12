package step_definitions.hooks;

import base.Base;
import io.cucumber.java.*;

public class Hooks extends Base {

    @Before
    public void driverSetup() {
        driverInit(browser);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

//    @Before(order = 0)
//    public void doSomethingBefore() {
//        System.out.println("----------------STARTING TEST----------------");
//    }
//
//
//    @Before(value = "@First", order = 1)
//    public void doSomethingBeforeFirst() {
//        System.out.println("This will run before the first test");
//    }
//
//    @Before("@Second")
//    public void doSomethingBeforeSecond() {
//        System.out.println("This will run before the second test");
//    }
//
//    @After(order = 0)
//    public void doSomethingAfter() {
//        System.out.println("----------------ENDING TEST----------------");
//    }
//
//    @After("@First")
//    public void doSomethingElseAfterFirst() {
//        System.out.println("This will run after first test");
//    }
//
//    @After("@Second")
//    public void doSomethingElseAfterSecond() {
//        System.out.println("This will run after second test");
//    }

}
