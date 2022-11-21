package hotels

import org.springframework.web.servlet.ModelAndView

class TestController {
    boolean hotelsShown
    def knownCountries = Country.findAll()

    def index() {
//        render 'Hello world!'
//        respond([name: 'World', stars:4])
        respond([hotels: Hotel.list(),countries:Country.list()])
    }

    def hotels() {
        respond([hotels: Hotel.list()])
    }

    def findHotels(String hotel) {
        flash.testMsg = hotel

        def c = Hotel.createCriteria()
        List<Hotel> results
        if (params.selection == "") {
            results = c.list {
                like("name","%${hotel}%")
            }
        } else {
            results = c.list {
                like("name","%${hotel}%")
                and {
                    eq("country",Country.findByName(params.selection))
                }
            }
        }
        println "COUNTRY = "+params.selection


        render(view:"index", model: [hotels: results,countries:Country.list()])
//        return new ModelAndView("/test/index",[hotels: results])
    }
}
