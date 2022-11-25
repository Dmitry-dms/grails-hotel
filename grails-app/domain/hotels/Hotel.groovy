package hotels

class Hotel {
    String name, site
    Integer stars
    Country country
    static belongsTo = [country: Country]
    static constraints = {
        name unique: ['name', 'country'], blank: false, maxSize: 255
        country blank: false
        stars range: 1..5, blank: false
        site blank: true, url: true, nullable: true
    }
}
