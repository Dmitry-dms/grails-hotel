package hotels

import grails.gorm.PagedResultList
import grails.web.servlet.mvc.GrailsParameterMap


class HotelService {
    def list(GrailsParameterMap params) {
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

        PagedResultList result
        def c = Hotel.createCriteria()
        if (selectedCountry == "") {
            result = c.list(max: params.max, offset: params.offset) {
                like("name", "%$searchString%")
                order("stars", "desc")
                order("name", "asc")
            }
        } else {
            result = c.list(max: params.max, offset: params.offset) {
                like("name", "%$searchString%")
                eq("country", Country.findByName(selectedCountry))
                order("stars", "desc")
                order("name", "asc")
            }
        }
        result
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
