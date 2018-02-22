# Practice 08

## Goal
* First steps with websockets
* You will animate a slideware simultaneously on several browser. It should remind you something...

## Intro

Add the following dependencies 

| groupId | artifactId | version | scope |
|---------|------------|---------|-------|
|  ch.qos.logback | logback-classic | 1.1.8 ||
|  org.slf4j | slf4j-api |1.7.25 ||
|  javax.websocket | javax.websocket-api | 1.0 | provided |
|  javax.servlet | javax.servlet-api | 3.1.0 | provided |
|  com.fasterxml.jackson.core | jackson-databind |2.4.3 ||
 
 

## Provided content
All the front content, with the images, the javascript or the CSS are provided. You just have to create the backend.

The slides are based on [Reveal.js](https://revealjs.com/) a famous framework that allows you to organize your slides in 2 dimensions.

## The DTO
In `yncrea.pw08.web.data` create a `Slide` class with 3 `int`attributes:
* `indexv` for the vertical position in the slides matrix
* `indexh` for the horizontal position in the slides matrix
* `indexf` for the fragment to show for the current slide

This DTO represents the coordinates of the info you want to show. This DTO is exchanged with the Javascript.

## The container of the current slide
In `yncrea.pw08.web.controller`, create a `CurrentSlideHolder`. You will implement it as a singleton (only one possible instance of that class for the whole application)
* it has 2 attributes
  * a `Slide` attribute with its getter and setter
  * a `CurrentSlideHolder` attribute that represent the only instance of this class.
  
* create an empty private constructor, it will lock the possibility to instanciate this class from the outside of this implementation
* create a static method `getInstance` that returns the unique `CurrentSlideHolder` instance.
    * if the inner attribute is null, call the constructor
    * return the inner attribute.
    
The only possible interactions with this class are:
* `CurrentSlideHolder.getInstance().getSlide()`
* `CurrentSlideHolder.getInstance().setSlide(...)`

## Decoding / Encoding

A JSON String will be exchanged between the frontend and the backend. You will have to write utilities to do the transformations between the JSON String and the DTO. Yes, this time, it is not automagic ;)

### SlideDecoder
* It implements `Decoder.Text<Slide>`
* In the `init` method, it instanciates an `ObjectMapper` from Jackson and stores it in an attribute.
* In the `decode` method, it calls the `readValue` of the mapper in order to transform the string parameter in a Slide instance.
* Be smart for the exception handling and the other methods to implement.

### SlideEncoder

If you understood the Decoder, let's go for the Encoder!
Tip : use the `writeValueAsString` of the mapper this time.

## The controller
In the `yncrea.pw08.web.controller` package, there is `RevealController`.

All the boilerplate code is commented or provided, just refer to the course and complete its implementation. Each time you receive a Slide command, you store this slide in the holder, then you broadcast it.

## That's it ! 
Deploy on a Tomcat then play with the app.
* index.html is the "public" view for the and is refreshed according to the admin commands
* admin.html is the "speaker" view that pilots all the others ;)