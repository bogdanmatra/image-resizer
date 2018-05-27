## Simple image resizing service with Spring Boot


#### How to run
* `mvn spring-boot:run`
* Go to: `http://localhost:8080/image/show/original/byk?reference=b1.jpg`

#### Selectors
/image/show/`{type}`/byk?reference=`{reference}` 

`type` - One of the following: `original`, `thumbnail`, `poster`

`reference` - One of the available images:
`b1.jpg`, `b2.jpg`, `b3.jpg`, `b4.jpg`, `b5.jpg`

#### Resize configuration `resources/resize_configuration.json`:
* Height (int), the new height of the image in pixels
* Width (int), the new width of the image in pixels
* TODO: Add more parameters and map them to the resize library.

#### Structure
            ImageResolver
                /      \
        ImageResizer    ImageStorage
            /
    ResizeConfiguration
    
All requests are directed to the `ImageResolver`.
If the selector is `original`, the image is retrieved directly from the `ImageStorage`.

If the selector has a different value, the configuration mapped to the selector is retrieved from the `ResizeConfiguration`.
The image is resized according to the configuration by the `ImageResizer`, saved to the `ImageStorage` and sent to the client.
Future requests will retrieve the resized image directly from the `ImageStorage`.

OBS: Resized images are saved as {selector}_originalFileName.jpg (ex: `thumbnail_b1.jpg`).