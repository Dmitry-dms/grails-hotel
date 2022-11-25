package hotels


import grails.validation.ValidationException
import grails.web.servlet.mvc.GrailsParameterMap

class HotelService {
    def list(GrailsParameterMap params) {
        return Hotel.list(params)
    }

    private String selectedCountry = ""
    private String searchHotelText = ""

    String getSearchInput() {
        return searchHotelText
    }

    void clearInputs() {
        searchHotelText = ""
        selectedCountry = ""
    }

    List<Hotel> findHotels(GrailsParameterMap params) {
        if (params.hotelTextInput != null) {
            searchHotelText = params.hotelTextInput
        }
        if (params.selectedCountry != null) {
            selectedCountry = params.selectedCountry
        }

        def c = Hotel.createCriteria()
        def result = c.list(max: params.max, offset: params.offset) {
            ilike("name", "%${searchHotelText}%")
            if (selectedCountry != "") {
                eq("country", Country.findByName(selectedCountry))
            }
            order("stars", "desc")
            order("name", "asc")
        }
        result
    }

    void save(Hotel hotel) {
        if (hotel.validate()) {
            hotel.save(flush: true)
        } else {
            throw new ValidationException("Failed to validate", hotel.errors)
        }
    }


    def get(Long id) {
        return Hotel.get(id)
    }

    void update(Hotel hotel) throws ValidationException{
        if (hotel.validate()) {
            hotel.save(flush: true)
        } else {
            throw new ValidationException("Failed to validate", hotel.errors)
        }
    }

    def delete(Long id) {
        try {
            def hotel = get(id)
            hotel.delete(flush: true)
        } catch (Exception e) {
            println(e.getMessage())
            return false
        }
        return true
    }

}
