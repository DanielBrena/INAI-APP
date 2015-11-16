//
//  InterfaceControllerResultado.swift
//  WatchOs2
//
//  Created by Daniel Brena Aquino on 15/11/15.
//  Copyright © 2015 Daniel Brena Aquino. All rights reserved.
//

import WatchKit
import Foundation


class InterfaceControllerResultado: WKInterfaceController {
    var datos_seleccionados = [Dato]()
    var preguntas_seleccionadas = [Pregunta]()
    
    @IBOutlet var resultado_final: WKInterfaceLabel!
    @IBAction func cargarPrincipal() {
      
        self.popToRootController()
    }
    
    
    override func awakeWithContext(context: AnyObject?) {
        super.awakeWithContext(context)
        self.datos_seleccionados = ((context as! NSDictionary)["data_datos"] as? [Dato])!
        self.preguntas_seleccionadas = ((context as! NSDictionary)["data_preguntas"] as? [Pregunta])!
        
        print(" \(self.preguntas_seleccionadas.count)  \(self.datos_seleccionados.count)")
        self.resultado_final.setText("$\(generarResultado())0")
        
    }
    
    func generarPreguntas()->Int{
        let cont:Int = self.preguntas_seleccionadas.count
        
        return cont + 1
    }
    
    func generarDatos()->(estandar:Int,sensible:Int,especial:Int){
        var estandar:Int = 0
        var sensible:Int = 0
        var especial:Int = 0
        
        for var i = 0; i < self.datos_seleccionados.count; i++ {
            if self.datos_seleccionados[i].categoria?.id == "1"{
                estandar = estandar + 1
            }else if self.datos_seleccionados[i].categoria?.id == "2" {
                sensible = sensible + 1
            }else {
                especial = especial + 1
            }
        }
        
        return (estandar,sensible,especial)
    }
    
    func generarResultado()->Double{
        var resultado:Double = 0.0
        var nominador:Int = generarDatos().estandar  *  Int(CategoriaDatoDAO().selectById("1").valor!)!
        + generarDatos().sensible *  Int(CategoriaDatoDAO().selectById("2").valor!)! +
        generarDatos().especial *  Int(CategoriaDatoDAO().selectById("3").valor!)!
        
        print("nominador \(nominador)")
        
        var denominador:Int = generarDatos().estandar +  generarDatos().sensible + generarDatos().especial
        
        print("denominador \(denominador)")
        print(generarPreguntas())
        print(ConfiguracionDAO().selectByID("1").valor)
        
        resultado = (Double(nominador) / Double(denominador) ) * ( Double(generarPreguntas())  * Double(ConfiguracionDAO().selectByID("1").valor!)!)
        
        print(resultado)
        
        return resultado
    }
    
    
}
