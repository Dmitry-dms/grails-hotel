package hotels

class Country {
    String name
    String capital

    static hasMany = [hotels: Hotel]
    static constraints = {
        name unique:true, blank:false, maxSize:255
        capital unique:true,blank:false, maxSize:128
    }
}
