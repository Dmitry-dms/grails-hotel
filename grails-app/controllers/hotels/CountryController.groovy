package hotels

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CountryController {

    CountryService countryService
    // Необходимо обнулить поисковую строку при переходе в другой пункт меню.
    // FIXME(@Dmitry-dms): Есть ли лучший способ? Жизненный цикл контроллера?
    HotelService hotelService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max,Integer offset) {
        hotelService.searchString = ""
        params.max = max ?: 3
        params.offset = offset ?: 0
        def res = countryService.findCountrySubstring(params)
        respond res, model: [countries    : res,
                             countriesCount: res.getTotalCount(),
                             searchString: countryService.searchString]
    }


    def create() {
        respond new Country(params)
    }

    def save() {
        try {
            countryService.save(params)
        } catch (ValidationException e) {
            respond country.errors, view: 'create'
            return
        }
        redirect(action: "index")
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
        redirect(action: "index")
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        countryService.delete(id)

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
