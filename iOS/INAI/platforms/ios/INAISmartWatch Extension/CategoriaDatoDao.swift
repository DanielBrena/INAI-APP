//
//  Query.swift
//  WatchOs2
//
//  Created by Daniel Brena Aquino on 13/11/15.
//  Copyright © 2015 Daniel Brena Aquino. All rights reserved.
//

/**

    Clase CategoriaDatoDAO
*/

class CategoriaDatoDAO  {
    func selectAll()->[CategoriaDato]{
        var arreglo = [CategoriaDato]()
        arreglo.append(CategoriaDato(id: "1",
                    nombre: "Nivel estandar",
                    descripcion: "Esta categoría considera información de identificación, contacto datos laborales y académicos de una persona física identificada o identificable.",
                    valor:"1",color: "#4CAF50"))
        
        arreglo.append(CategoriaDato(id: "2",
                    nombre: "Nivel sensible",
                    descripcion: "Esta categoría contempla los datos que permiten conocer la ubicación física de la persona, tales como la dirección física e información relativa al tránsito de las personas dentro y fuera del país.",
                    valor:"2",color: "#FFC107"))
            
        arreglo.append(CategoriaDato( id: "3",
                    nombre: "Nivel especial",
                    descripcion: "Esta categoría corresponde a los datos cuya propia naturaleza, o bien debido a un cambio excepcional en el contexto de las operaciones usuales de la organización, pueden causar daño directo al patrimonio o seguridad de los titulares.",
                    valor:"3",color: "#F44336"))
        return arreglo
    }
    
    func selectById(id:String)->CategoriaDato{
        var categoriaDato:CategoriaDato?
        for var i = 0; i < selectAll().count; i++ {
            if selectAll()[i].id == id{
                categoriaDato = selectAll()[i]
                break
            }
        }
        return categoriaDato!
    }
}
