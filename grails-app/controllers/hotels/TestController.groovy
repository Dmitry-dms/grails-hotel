package hotels

import org.springframework.web.servlet.ModelAndView

class TestController {

    static scope = "session"
    boolean hotelsShown
    def knownCountries = Country.findAll()
    HotelService hotelService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond([hotels: hotelService.list(params), countries: Country.list(), hotelCount: hotelService.count()])
    }


    def hotels() {
        respond([hotels: Hotel.list()])
    }

    def nextAction() {
        println("NEXT " + flash.hotelName)

    }
    String lastHotel
    String lastSelectedCountry

    def findHotels(Integer max) {
        String hotel = params.hotel
        if (hotel != null) {
            lastHotel = hotel
        }
        String country = params.selection
        if (country != "") {
            lastSelectedCountry = country
        }

        println params.selection
        def c = Hotel.createCriteria()
        ArrayList<Hotel> results
        if (lastSelectedCountry == null) {
            results = c.list {
                like("name", "%${lastHotel}%")
            }
        } else {
            results = c.list {
                like("name", "%${lastHotel}%")
                eq("country", Country.findByName(lastSelectedCountry))
            }
        }

        Integer max2, offset;
        max2 = Math.min(max ?: 10, 100)
        if (params.offset == null) {
            offset = 0
        } else {
            Integer offset2 = Integer.parseInt(params.offset)
            offset = offset2
        }
        ArrayList<Hotel> results2
        if (max == null) {
            results2 = results.drop(offset).take(max2)
        } else {
            results2 = results.drop(offset).take(max)
            println "$max and $offset size = ${results2.size()} hotel = $lastHotel"
        }

        render(view: "index", model: [hotels    : results2,
                                      countries : Country.list(),
                                      hotelCount: results.size(),
                                      hotelName : lastHotel])
    }

}
