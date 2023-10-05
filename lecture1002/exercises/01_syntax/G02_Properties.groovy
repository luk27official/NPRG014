class City {
    String name
    int size
    boolean capital = false
    
    static def create(String n, int v, boolean e = false) {
        return new City(name: n, size: v, capital: e)
    }

    @Override String toString() {
        (capital ? "Capital city " : "City ") + "of ${name}, population: ${size}"
    }
}

println City.create("Brno", 400000).dump()
def praha = City.create("Praha", 1300000, true).dump()
println praha

City pisek = new City(name: 'Písek', size: 25000, capital: false)
City tabor = new City(size: 35000, capital: false, name: 'Tábor')
City pardubice = new City(name: 'Pardubice', size: 90000, capital: false)
City praha = new City(name: "Praha", size: 1300000, capital: true)

println pardubice.dump()

println pisek.dump()
pisek.size = 25001
println pisek.dump()

println tabor
//TASK Provide a customized toString() method to print the name and the population
assert 'City of Písek, population: 25001' == pisek.toString()
assert 'Capital city of Praha, population: 1300000' == praha.toString()