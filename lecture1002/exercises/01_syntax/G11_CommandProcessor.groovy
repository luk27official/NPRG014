class Plane {
    private int thrust = 0
    private boolean inFlight = false
    private boolean engineOn = false
    private int altitude = 0

    private void startEngine() {
        println 'Starting the engine'
        engineOn = true
    }

    private void stopEngine() {
        println 'Stopping the engine'
        engineOn = false
    }

    private void adjustThrust(int value) {
        thrust = value
        println 'Adjusting thrust to ' + thrust
    }

    private void climbTo(int altitude) {
        println "Changing altitude to $altitude"
        this.altitude = altitude
    }

    void performCommand(String value, Closure code) {
        //this.with(code) //this works as well
        Closure customCommand = code.clone() //to ensure that it does not get changed
        customCommand.delegate = this
        customCommand.call()
    }

    void call(String value, Closure code) {
        this.performCommand(value, code)
    }
}

final takeoff = {
    if (!engineOn) startEngine()
    adjustThrust 10
    climbTo 5000
    adjustThrust 5
}

final land = {
    adjustThrust 3
    climbTo 0
    adjustThrust 0
    stopEngine()
}

//TASK Implement the suggested performCommand() method to set the delegate so that the following code passes
final plane = new Plane()
plane.performCommand('Take off', takeoff)
println '*** We are in flight now ***'
plane.performCommand('Land', land)

//TASK Make the following code pass, too. Use the doCall() method to handle function calls on the Plane objects.
plane('Take off', takeoff)
println '*** We are in flight now ***'
plane('Land', land)