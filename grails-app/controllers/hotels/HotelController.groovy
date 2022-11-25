package hotels

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class HotelController {

    HotelService hotelService
    CountryService countryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    private Integer maxPageElements = grailsApplication.config.getProperty('ui.default_max_elements', Integer, 10)

    // Отображается на главной
    def start() {
        render(view: "start", model: [countries      : Country.list(),
                                      inputSearchText: hotelService.getSearchInput()])
    }

    def index(Integer max, Integer offset) {
        countryService.clearSearchInput()
        params.max = max ?: maxPageElements
        params.offset = offset ?: 0
        def res = hotelService.findHotels(params)
        respond res, model: [hotels         : res,
                             hotelsCount    : res.getTotalCount(),
                             inputSearchText: hotelService.getSearchInput()]
    }


    // Используется в поиске с главной страницы
    def findHotels(Integer max, Integer offset) {
        params.max = max ?: maxPageElements
        params.offset = offset ?: 0
        def res = hotelService.findHotels(params)
        hotelService.clearInputs()
        countryService.clearSearchInput()
        render(view: "findHotels", model: [hotels    : res, hotelCount: res.getTotalCount()])
    }


    def create() {
        respond new Hotel(params)
    }

    def save(Hotel hotel) {
        try {
            hotelService.save(hotel)
        } catch (ValidationException e) {
            flash.error = message(code: 'validation_error')
            respond hotel.errors, view: 'create'
            return
        }
        flash.save = message(code: 'hotel.tip.create')
        redirect(action: "index")
    }

    def edit(Long id) {
        respond hotelService.get(id)
    }

    def update(Hotel hotel) {
        if (hotel == null) {
            notFound()
            return
        }
        try {
            hotelService.update(hotel)
        } catch (ValidationException e) {
            flash.error = message(code: 'validation_error')
            respond hotel.errors, view: 'edit'
            return
        }
        flash.update = message(code: 'hotel.tip.update')
        redirect(action: "index")
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        if (hotelService.delete(id)) {
            flash.delete = message(code: 'hotel.tip.delete')
        } else {
            flash.delete_error = message(code: 'error.delete')
        }
        redirect action: "index", method: "GET"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hotel.label', default: 'Hotel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
