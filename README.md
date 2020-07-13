# Movies Rest API
## API to get movies info
Made with Spring boot, all movies data are support by H2 database.

## Build
If you want to build this app ant run on your localhost follow next steps.
    1. Clone this repository in any folder of your PC
    2. Enter to project root folder (cd)
    3. Build with *mvn*
    ```bash
    mvn clean package
    ```
    4. Run app
    ```bash
    mvn spring-boot:run
    ```
    5. Enter with your browser to localhost:8080

## Endpoints

GET /api/v1/genders
GET /api/v1/movies
GET /api/v1/movies/{id}
GET /api/v1/movies/order
GET /api/v1/movies/top/{top}  //top is the number of what top do you want, cal call without top parameter so it 10 as default.

