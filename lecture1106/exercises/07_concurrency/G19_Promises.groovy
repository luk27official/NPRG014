import groovyx.gpars.dataflow.Dataflow
import static groovyx.gpars.dataflow.Dataflow.*
import groovyx.gpars.dataflow.DataflowVariable

def engineCheck = new DataflowVariable()
def tyrePressure = new DataflowVariable()
def radarOn = new DataflowVariable()
def electricityCheck = new DataflowVariable()

task {
    println "Checking the engine"
    sleep 3000
    engineCheck << true
    println "Engine ok"
}

task {
    println "Preparing the tyres"
    sleep 4000
    tyrePressure << true
    println "Tyres ok"
}

task {
    // firstly check electricity
    println "Turning radar on"
    sleep 1000
    
    if(!electricityCheck.get()) {
        radarOn << false
        println "Electricity not ok, radar not turned on"
    }
    else {
        radarOn << true
        println "Radar ok"
    }
}

task {
    println "Checking electricity"
    sleep 4000
    if (System.currentTimeInMillis() % 2 == 0) {
        electricityCheck << false
        println "Electricity not ok"
    }
    else {
        electricityCheck << true
        println "Electricity ok"
    }
}

//TASK: Radar can only be turned on after electricity is checked. Add an electricity-checking task
//and wire it so that the radar can only turned on after the electricity check finishes correctly.

boolean ready = [engineCheck, tyrePressure, radarOn].every {it.val}
if(ready) {
    println 'Taking off'
} else {
    println 'Staying on the ground today'
}