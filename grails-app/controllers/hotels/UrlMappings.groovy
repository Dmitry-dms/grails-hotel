package hotels

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/"(view:"index")
        "/"(controller:"hotel", action: "start")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
