package hotels

import grails.web.servlet.mvc.GrailsParameterMap

import javax.transaction.Transactional

class CountryService {
    List<Country> list(GrailsParameterMap params) {
        return Country.list(params)
    }

    String searchString

    List<Country> findCountrySubstring(GrailsParameterMap params) {
        if (params.search_subsctr != null) {
            searchString = params.search_subsctr
        }
        ArrayList<Country> results
        def c = Country.createCriteria()
        results = c.list {
            like("name", "%$searchString%")
        }
        results
    }

    List<Country> getPaginated(GrailsParameterMap params, List<Country> countries) {
        params.max = params.max ?: 10
        params.offset = params.offset ?: 0
        if (params.max instanceof String) {
            params.max = Integer.parseInt(params.max)
        }
        if (params.offset instanceof String) {
            params.offset = Integer.parseInt(params.offset)
        }
        countries.drop(params.offset).take(params.max) as List<Country>
    }

    int count() {
        Country.count()
    }

    def save(Country country) {
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
        save(country)
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
