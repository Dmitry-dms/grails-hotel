package hotels


import grails.validation.ValidationException
import grails.web.servlet.mvc.GrailsParameterMap


class CountryService {
    List<Country> list(GrailsParameterMap params) {
        return Country.list(params)
    }

    private String searchCountryText = ""

    String getSearchInput() {
        return searchCountryText
    }

    void clearSearchInput() {
        searchCountryText = ""
    }

    def findCountries(GrailsParameterMap params) {
        if (params.countryTextInput != null) {
            searchCountryText = params.countryTextInput
        }
        def c = Country.createCriteria()
        def results = c.list(max: params.max, offset: params.offset) {
            ilike("name", "%${searchCountryText}%")
        }
        results
    }


    void save(Country country) throws ValidationException {
        if (country.validate()) {
            country.save(flush: true)
        } else {
            throw new ValidationException("Failed to validate", country.errors)
        }
    }

    def get(Long id) {
        Country.get(id)
    }

    void update(Country country) throws ValidationException{
        if (country.validate()) {
            country.save(flush: true)
        } else {
            throw new ValidationException("Failed to validate", country.errors)
        }
    }

    def delete(Long id) {
        try {
            def c = get(id)
            c.delete(flush: true)
        } catch (Exception e) {
            println(e.getMessage())
            return false
        }
        return true
    }
}
