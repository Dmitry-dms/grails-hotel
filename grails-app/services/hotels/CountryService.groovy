package hotels

import grails.web.servlet.mvc.GrailsParameterMap


class CountryService {
    List<Country> list(GrailsParameterMap params) {
        return Country.list(params)
    }

    String searchString = ""

    List<Country> findCountrySubstring(GrailsParameterMap params) {
//        params.max = params.max ?: 3
//        params.offset = params.offset ?: 0
//        if (params.max instanceof String) {
//            params.max = Integer.parseInt(params.max)
//        }
//        if (params.offset instanceof String) {
//            params.offset = Integer.parseInt(params.offset)
//        }
        if (params.search_subsctr != null) {
            searchString = params.search_subsctr
        }
        def c = Country.createCriteria()
        def results = c.list(max: params.max, offset: params.offset) {
            like("name", "%$searchString%")
        }
        results
    }


    int count() {
        Country.count()
    }

    def save(GrailsParameterMap params) {
        Country country = new Country(params)
        if (!country.save(flush: true)) {
            country.errors.each {
                println it
            }
        }
    }

    def get(Long id) {
        Country.get(id)
    }

    def update(Long id, GrailsParameterMap params) {
        Country country = get(id) as Country
        country.properties = params
        if (!country.save(flush: true)) {
            country.errors.each {
                println it
            }
        }
    }

    def delete(Long id) {
        try {
            Country c = get(id) as Country
            c.delete(flush: true)
        } catch (Exception e) {
            println(e.getMessage())
            return false
        }
        return true
    }
}
