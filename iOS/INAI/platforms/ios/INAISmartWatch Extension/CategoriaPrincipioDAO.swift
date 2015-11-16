//
//  CategoriaPrincipio.swift
//  WatchOs2
//
//  Created by Daniel Brena Aquino on 14/11/15.
//  Copyright © 2015 Daniel Brena Aquino. All rights reserved.
//

class CategoriaPrincipiosDAO {
    func selectAll()->[CategoriaPrincipios]{
        
        var arreglo = [CategoriaPrincipios]()
        
        arreglo.append(CategoriaPrincipios(
            id:"1",
            nombre: "Transparencia",
            descripcion: "La percepción del titular respecto a la claridad del tratamiento que el responsable le da a sus datos."
        ))
        
         arreglo.append(CategoriaPrincipios(
            id:"2",
            nombre: "Confianza",
            descripcion: "La confianza que el titular experimenta al proporcionar su información personal al responsable, a cambio de recibir un producto o servicio."

        ))
        
         arreglo.append(CategoriaPrincipios(
            id:"3",
            nombre: "Control",
            descripcion: " La capacidad del titular para ejercer de manera efectiva sus derechos de Acceso, Rectificación, Cancelación y Oposición de los datos que proporciona al responsable."
        ))
        
         arreglo.append(CategoriaPrincipios(
            id:"4",
            nombre: "Valoración",
            descripcion:"La percepción del titular respecto a si sus datos serán explotados para un fin distinto al original de cuando fueron recabados."
        ))
        
        return arreglo
    }
    
    func selectById(id:String)->CategoriaPrincipios{
        var categoriaPrincipio:CategoriaPrincipios?
        for var i = 0; i < selectAll().count; i++ {
            if selectAll()[i].id == id{
                categoriaPrincipio = selectAll()[i]
                break
            }
        }
        return categoriaPrincipio!
    }
}