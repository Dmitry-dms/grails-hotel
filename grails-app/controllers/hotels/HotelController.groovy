package hotels

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class HotelController {

    HotelService hotelService
    CountryService countryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    // Отображается на главной
    def start() {
        hotelService.clearSearchInput()
        countryService.clearSearchInput()
        render(view: "start", model: [countries   : Country.list(),
                                      inputSearchText: hotelService.getSearchInput()])
    }

    def index(Integer max, Integer offset) {
        countryService.clearSearchInput()
        params.max = max ?: 5
        params.offset = offset ?: 0
        def res = hotelService.findHotels(params)
        respond res, model: [hotels     : res,
                             hotelsCount: res.getTotalCount(),
                             inputSearchText: hotelService.getSearchInput()]
    }


    // Используется в поиске с главной страницы
    def findHotels(Integer max,Integer offset) {
        params.max = max ?: 5
        params.offset = offset ?: 0
        def res = hotelService.findHotels(params)
        render(view: "findHotels",
                model: [hotels    : res,
                        hotelCount: res.getTotalCount()])
    }


    def create() {
        respond new Hotel(params)
    }

    def save(Hotel hotel) {
        try {
            hotelService.save(hotel)
        } catch (ValidationException e) {
            flash.error = "Отель уже присуствует в базе данных"
            respond hotel.errors, view: 'create'
            return
        }
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
            flash.error = "Введенные данные соотвествуют существующей записи в базе данных"
            respond hotel.errors, view: 'edit'
            return
        }
        redirect(action: "index")
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        hotelService.delete(id)
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
