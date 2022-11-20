package hotels

class Country {
    String name, capital

    static hasMany = [hotels: Hotel]
    static constraints = {
    }
}
