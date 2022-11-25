package hotels

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class HotelController {

    HotelService hotelService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    // Отображается на главной
    def start() {
        hotelService.searchString = ""
        render(view: "start", model: [countries   : Country.list(),
                                      searchString: hotelService.searchString])
    }

    def index(Integer max, Integer offset) {
        params.max = max ?: 5
        params.offset = offset ?: 0
        def res = hotelService.list(params)
        respond res, model: [hotels     : res,
                             countries  : Country.list(),
                             hotelsCount: res.getTotalCount()]
    }

    // Используется в поиске со справочника отелей
    def showHotels(Integer max,Integer offset) {
        params.max = max ?: 5
        params.offset = offset ?: 0
        def results = hotelService.findHotelsSubstring(params)
        render(view: "index", model: [hotels     : results,
                                      hotelsCount: results.getTotalCount(),
                                      searchString: hotelService.searchString]
        )
    }

    // Используется в поиске с главной страницы
    def findHotels(Integer max,Integer offset) {
        params.max = max ?: 5
        params.offset = offset ?: 0
        def results = hotelService.findHotelsSubstring(params)
        render(view: "findHotels",
                model: [hotels    : results,
                        hotelCount: results.getTotalCount()])
    }

    def show(Long id) {
        respond hotelService.get(id)
    }

    def create() {
        respond new Hotel(params)
    }

    def save() {
        try {
            hotelService.save(params)
        } catch (ValidationException e) {
            respond hotel.errors, view: 'create'
            return
        }
        redirect(action: "showHotels")
    }

    def edit(Long id) {
        respond hotelService.get(id)
    }

    def update(Long id) {
        if (id == null) {
            notFound()
            return
        }
        try {
            hotelService.update(id, params)
        } catch (ValidationException e) {
            respond hotel.errors, view: 'edit'
            return
        }
        redirect(action: "showHotels")
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        hotelService.delete(id)
        redirect action: "showHotels", method: "GET"
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
