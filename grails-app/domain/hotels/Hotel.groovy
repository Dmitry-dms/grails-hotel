package hotels

class Hotel {
    String name
    Integer stars
//    Country country
    static belongsTo = [country: Country]
    static constraints = {
    }
}
