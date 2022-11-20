package hotels

import grails.gorm.services.Service

@Service(Hotel)
interface HotelService {

    Hotel get(Serializable id)

    List<Hotel> list(Map args)

    Long count()

    void delete(Serializable id)

    Hotel save(Hotel hotel)

}