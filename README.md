# pango-service-apis
pango services endpoints
- Run [config-server](https://github.com/CEITECHS/pango-config-server) or [docker](https://hub.docker.com/r/iamiddy/pango-config-server) 
- Run the service or [docker](https://hub.docker.com/r/iamiddy/pango-service-apis/) and point `GET http://localhost:8090/rentals` 
- Search for properties `GET /properties?latitude=-6.662951&longitude=39.166650&propertyPurpose=HOME&radius=50&minPrice=0 HTTP/1.1
                         Host: localhost:8090
                         Accept: application/json
                         user-token: QEZJ22pp9OKN0C8yLmnG+DPxAIZwqKoLiMKtobaTcJKdbgumlCIWyDsIviwbbqG8KH3OX2OncwcV+g/1T0d7myJnkbvBzNIHTURrBta9RTA=
                         Cache-Control: no-cache`  
 
# TODOS
 1. Enable Discovery Server - to allow service registration.
