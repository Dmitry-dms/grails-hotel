package hotels

class Hotel {
    String name,site
    Integer stars
    Country country
    static belongsTo = [country: Country]
    static constraints = {
    }
}
