package hotels

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CountryController {

    CountryService countryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def res = countryService.list(params)
        respond res, model: [countries    : res,
                             countriesCount: countryService.count()]
    }

    def findCountries(Integer max) {

        ArrayList<Country> results = countryService.findCountrySubstring(params)
        ArrayList<Country> paginated = countryService.getPaginated(params, results)

        render(view: "index", model: [countries     : paginated,
                                      countriesCount: results.size()], searchString: countryService.searchString,
                )
    }

    def show(Long id) {
        respond countryService.get(id)
    }

    def create() {
        respond new Country(params)
    }

    def save(Country country) {
        if (country == null) {
            notFound()
            return
        }

        try {
            countryService.save(country)
        } catch (ValidationException e) {
            respond country.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'country.label', default: 'Country'), country.id])
                redirect(action: "index")
            }
            '*' { respond country, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond countryService.get(id)
    }

    def update(Long id) {
        if (id == null) {
            notFound()
            return
        }
        try {
            countryService.update(id, params)
        } catch (ValidationException e) {
            respond country.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
//                flash.message = message(code: 'default.updated.message', args: [message(code: 'country.label', default: 'Country'), country.id])
                redirect(action: "index")
            }
            '*' { respond country, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        countryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'country.label', default: 'Country'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'country.label', default: 'Country'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
