package hotels

class Country {
    String name, capital

    static hasMany = [hotels: Hotel]
    static constraints = {
        name uniqie:true, blank:false, maxSize:255
        capital blank:false, maxSize:128
    }
}
