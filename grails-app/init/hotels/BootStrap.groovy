package hotels

import groovy.json.JsonSlurper

class BootStrap {

    def init = { servletContext ->
        def countries = ['Russia':'Moscow','Britain':'London','Japan':'Tokyo','USA':'Washington','France':'Paris'].collect {
            k,v ->
               new Country(name: k,capital: v).save()
        }
        Random rnd = new Random()
        rnd.setSeed(System.currentTimeMillis())
        Country cnt

//        for (i in 0..40) {
//            Integer r = rnd.nextInt(countries.size())
//            cnt = countries.get(r) as Country
//            new Hotel(name: "Hotel ${i}",site: 'https://yandex.ru/', stars: rnd.nextInt(6),country: cnt).save()
//        }

        //Взаимодействие с API описано на сайте https://support.travelpayouts.com/hc/ru/articles/115000343268-API-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D1%85-%D0%BE%D1%82%D0%B5%D0%BB%D0%B5%D0%B9
        JsonSlurper slurper = new JsonSlurper()
        for (i in 0..10) {
            def baseUrl = new URL("https://engine.hotellook.com/api/v2/lookup.json?query=${52+i},${80+i}&lang=en&lookFor=hotel&limit=10")
            HttpURLConnection connection = (HttpURLConnection) baseUrl.openConnection();
            connection.addRequestProperty("Accept", "application/json")
            connection.with {
                doOutput = true
                requestMethod = 'GET'
                Map parsedJson = slurper.parseText(content.text)
                def hotel = parsedJson['results']['hotels']
                String site;
                for (item in hotel) {
                    String name = item['name']
                    Integer r = rnd.nextInt(countries.size())
                    cnt = countries.get(r) as Country
                    Integer stars = rnd.nextInt(6)
                    if (stars==5) {
                        site = 'https://yandex.ru'
                    }
                    new Hotel(name: name,site: site, stars: stars,country: cnt).save()
                }
            }
        }
    }
    def destroy = {
    }
}
