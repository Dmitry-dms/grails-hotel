package hotels

import grails.web.servlet.mvc.GrailsParameterMap

class HotelService {
    List<Hotel> list(GrailsParameterMap params) {
        return Hotel.list(params)
    }

    List<Hotel> allHotels = Hotel.list()
    String selectedCountry, searchString

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
                and {
                    order("stars", "desc")
                    order("name", "asc")
                }
            }
        } else {
            results = c.list {
                like("name", "%$searchString%")
                eq("country", Country.findByName(selectedCountry))
                and {
                    order("stars", "desc")
                    order("name", "asc")
                }
            }
        }
        results
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

    Hotel get(Serializable id) {
        return Hotel.get(id)
    }

    void delete(Serializable id) {
        Hotel.delete(id)
    }

    Hotel save(Hotel hotel) {
        return Hotel.update(hotel)
    }

}
