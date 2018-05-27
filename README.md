### Simple image resizing service with Spring Boot


##### How to run
* `mvn spring-boot:run`
* Go to: `http://localhost:8080/image/show/original/byk?reference=b1.jpg`

##### Selectors
http://localhost:8080/image/show/`{type}`/byk?reference=`{reference}`
`type` - One of the following: [thumbnail, poster, original]
`reference` - One of the available images:
`b1.jpg`, `b2.jpg`, `b3.jpg`, `b4.jpg`, `b5.jpg`

##### Resize configuration `resources/resize_configuration.json`:
* Height (int), the new height of the image in pixels
* Width (int), the new width of the image in pixels
* TODO: Add more parameters and map them to the resize library.

##### Structure
        ImageResolver
          /     \
ImageResizer    ImageStorage
     /
ResizeConfiguration
