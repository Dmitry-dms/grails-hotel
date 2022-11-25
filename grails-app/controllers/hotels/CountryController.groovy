package hotels

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


class CountryController {

    CountryService countryService
    // Необходимо обнулить поисковую строку при переходе в другой пункт меню.
    // FIXME(@Dmitry-dms): Есть ли лучший способ? Жизненный цикл контроллера?
    HotelService hotelService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    private Integer maxPageElements = grailsApplication.config.getProperty('ui.default_max_elements', Integer, 10)

    def index(Integer max, Integer offset) {
        params.max = max ?: maxPageElements
        params.offset = offset ?: 0
        hotelService.clearInputs()
        def res = countryService.findCountries(params)
        respond res, model: [countries      : res,
                             countriesCount : res.getTotalCount(),
                             inputSearchText: countryService.getSearchInput()]
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
            flash.error = message(code: 'validation_error')
            respond country.errors, view: 'create'
            return
        }
        flash.save = message(code: 'country.tip.create')
        redirect(action: "index")
    }

    def edit(Long id) {
        respond countryService.get(id)
    }

    def update(Country country) {
        if (country == null) {
            notFound()
            return
        }
        try {
            countryService.update(country)
        } catch (ValidationException e) {
            flash.error = message(code: 'validation_error')
            respond country.errors, view: 'edit'
            return
        }
        flash.update = message(code: 'country.tip.update')
        redirect(action: "index")
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        if (countryService.delete(id)) {
            flash.delete = message(code: 'country.tip.delete')
        } else {
            flash.delete_error = message(code: 'error.delete')
        }

        redirect action: "index", method: "GET"
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
