package hotels

class BootStrap {

    def init = { servletContext ->
        def countries = ['Russia':'Moscow','Britain':'London','Japan':'Tokyo'].collect {
            k,v ->
                new Country(name: k,capital: v).save()
        }
        println "${Country.findByName('Russia').name}"
    }
    def destroy = {
    }
}
