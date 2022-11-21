package hotels

class BootStrap {

    def init = { servletContext ->
        def countries = ['Russia':'Moscow','Britain':'London','Japan':'Tokyo'].collect {
            k,v ->
                new Country(name: k,capital: v).save()
        }
        Random rnd = new Random()
        rnd.setSeed(System.currentTimeMillis())
        Country cnt
        for (i in 0..<100) {
            Integer r = rnd.nextInt(countries.size()-1)
            cnt = countries.get(r) as Country
            new Hotel(name: "Hotel ${i}",stars: rnd.nextInt(6),country: cnt).save()
        }
    }
    def destroy = {
    }
}
