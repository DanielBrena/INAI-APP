//
//  CategoriaDato.swift
//  WatchOs2
//
//  Created by Daniel Brena Aquino on 13/11/15.
//  Copyright © 2015 Daniel Brena Aquino. All rights reserved.
//


class CategoriaDato{
    var id:String?
    var nombre:String?
    var descipcion:String?
    var valor:String?
    var color:String?
    
    init(id:String,nombre:String,descripcion:String,valor:String,color:String){
        self.id = id
        self.nombre = nombre
        self.valor = valor
        self.descipcion = descripcion
        self.color = color
    }
    
}
class Dato {
    var id:String?
    var nombre:String?
    var descripcion:String?
    var categoria:CategoriaDato?
    var isChecked:Bool?
    
    init(id:String,nombre:String,descripcion:String,categoria:CategoriaDato,isChecked:Bool){
        self.id = id
        self.nombre = nombre
        self.descripcion = descripcion
        self.categoria = categoria
        self.isChecked = isChecked
    }

}


class CategoriaPrincipios {
    var id:String?
    var nombre:String?
    var descripcion:String?
    init(id:String,nombre:String,descripcion:String){
        self.id = id
        self.nombre = nombre
        self.descripcion = descripcion
    }

}
class Pregunta {
    var id:String?
    var pregunta:String?
    var categoria:CategoriaPrincipios?
    var isChecked:Bool?
    init(id:String,pregunta:String,categoria:CategoriaPrincipios,isChecked:Bool){
        self.id = id
        self.pregunta = pregunta
        self.categoria = categoria
        self.isChecked = isChecked
    }

}
class Configuracion{
    var id:String?
    var nombre:String?
    var valor:String?
    
    init(id:String,nombre:String,valor:String){
        self.id = id
        self.nombre = nombre
        self.valor = valor
    }

}