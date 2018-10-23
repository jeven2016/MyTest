package hello


def person = new Person();
person.name = "helow world";
print(person.name);

//assert person.name == "222"

//map
def map = ["w1": 10, "w2": 20]
w1 = "w1"
println map[w1];

//list
def list = [100, 200, 300];
println list[1]

//range
def ran = 1..3
ran.contains(1)
ran.from
ran.get(1)
ran.size()
ran.iterator().forEachRemaining() {
    val ->
        println "ran=" + val
        println "end"
}

//upto
1.upto(2) {
    val -> println "upto=" + val
}

//todo 3.2