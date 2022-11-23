package hotels

import grails.web.servlet.mvc.GrailsParameterMap

class HotelService {
    List<Hotel> list(GrailsParameterMap params) {
        return Hotel.list(params)
    }

    String selectedCountry = ""
    String searchString = ""

    List<Hotel> findHotelsSubstring(GrailsParameterMap params) {
        if (params.search_subsctr != null) {
            searchString = params.search_subsctr
        }
        if (params.selectedCountry != null) {
            selectedCountry = params.selectedCountry
        }
        ArrayList<Hotel> results
        def c = Hotel.createCriteria()
        if (selectedCountry == "") {
            results = c.list {
                like("name", "%$searchString%")
                order("stars", "desc")
                order("name", "asc")
            }
        } else {
            results = c.list {
                like("name", "%$searchString%")
                eq("country", Country.findByName(selectedCountry))
                order("stars", "desc")
                order("name", "asc")
            }
        }
        results
    }

    def save(GrailsParameterMap params) {
        Hotel h = new Hotel(name: params.name, stars: params.stars, site: params.site)
        def c = Country.findById(params.country.id)
        h.country = c
        if (!h.save()) {
            h.errors.each {
                println it
            }
        }
    }

    List<Hotel> getPaginated(GrailsParameterMap params, List<Hotel> hotels) {
        params.max = params.max ?: 10
        params.offset = params.offset ?: 0
        if (params.max instanceof String) {
            params.max = Integer.parseInt(params.max)
        }
        if (params.offset instanceof String) {
            params.offset = Integer.parseInt(params.offset)
        }
        hotels.drop(params.offset).take(params.max) as List<Hotel>
    }

    int count() {
        Hotel.count()
    }

    def get(Long id) {
        return Hotel.get(id)
    }

    def update(Long id, GrailsParameterMap params) {
        Hotel hotel = get(id) as Hotel
        hotel.properties = params
        hotel.save(flush: true)
    }

    def delete(Long id) {
        try {
            Hotel hotel = get(id) as Hotel
            hotel.delete(flush: true)
        } catch (Exception e) {
            println(e.getMessage())
            return false
        }
        return true
    }

}
