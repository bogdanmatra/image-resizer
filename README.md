### Simple image resizing service with Spring Boot


Resize configuration `resources/resize_configuration.json`:

* Height (int), the new height of the image in pixels
* Width (int), the new width of the image in pixels
* Quality (int), the JPG compression quality, from 0 - 100.
* Scale type (enumerator), how is the image resized when the new height and width are not in the same aspect ratio as the original image
    * Crop, cut of parts of the image that no longer fit the new aspect ratio
    * Fill, fill up the parts of the image that no longer fit the new aspect ration with a background-color specified by the Fill-color property
    * Skew, simply squeeze the image to fit the new height and width, this will make the image look bad in most cases
* Fill-color (hex value), used when the Scale-type is set to Fill.
* Type (enumerator), the type of the image returned: JPG, PNG