//
//  ConfiguracionDAO.swift
//  WatchOs2
//
//  Created by Daniel Brena Aquino on 15/11/15.
//  Copyright © 2015 Daniel Brena Aquino. All rights reserved.
//

class ConfiguracionDAO {
    func selectAll() ->[Configuracion]{
        var arreglo = [Configuracion]()
        
        arreglo.append(Configuracion(id: "1", nombre: "unidad_monetaria", valor: "150"))
            
        
        return arreglo
    }
    func selectByID(id:String)->Configuracion{
        var configuracion:Configuracion?
        for var i = 0; i < selectAll().count; i++ {
            if selectAll()[i].id == id{
                configuracion = selectAll()[i]
                break
            }
        }
        return configuracion!
    }
}