//
//  InterfaceController.swift
//  INAISmartWatch Extension
//
//  Created by Daniel Brena Aquino on 16/11/15.
//
//

import WatchKit
import Foundation


class InterfaceControllerDatos: WKInterfaceController {

    var datos = DatoDAO().selectAll()
    var datos_seleccionados = [Dato]()
    
    @IBOutlet var tableView: WKInterfaceTable!
    
    @IBAction func cambiarVista() {
        
        if self.datos_seleccionados.count == 0{
            self.mostrarAlerta()
        }else{
            self.pushControllerWithName("PreguntasCtrl", context: [ "data_datos":self.datos_seleccionados])
            
        }
        
        
    }
    
    func mostrarAlerta(){
        let cancel = WKAlertAction(title: "Entiendo", style: WKAlertActionStyle.Cancel, handler: { () -> Void in
            
        })
        
        let action = WKAlertAction(title: "Cancelar", style: WKAlertActionStyle.Default, handler: { () -> Void in
            
        })
        
        self.presentAlertControllerWithTitle("Mensaje", message: "Selecciona como minimo un dato.", preferredStyle: WKAlertControllerStyle.SideBySideButtonsAlert, actions: [cancel, action])
        
    }
    
    
    override func awakeWithContext(context: AnyObject?) {
        super.awakeWithContext(context)
        self.datos = DatoDAO().selectAll()
        setupTable()
        
        // Configure interface objects here.
    }
    @IBAction func recargar() {
        self.datos = DatoDAO().selectAll()
        
        setupTable()
        
    }
    
    override func willActivate() {
        // This method is called when watch view controller is about to be visible to user
        super.willActivate()
    }
    
    func setupTable() {
        tableView.setNumberOfRows(datos.count, withRowType: "DatoRow")
        
        for var i = 0; i < datos.count; ++i {
            //  let d = datos[i] as! Dato
            if let row = tableView.rowControllerAtIndex(i) as? DatoRow,
                let dato = self.datos[i] as? Dato{
                    
                    row.nombre.setText(dato.nombre)
                    
                    if dato.categoria?.id == "1"{
                        row.nombre.setTextColor(UIColorFromRGB(0x4CAF50))
                    }else if dato.categoria?.id == "2" {
                        row.nombre.setTextColor(UIColorFromRGB(0xFFC107))
                    }else {
                        row.nombre.setTextColor(UIColorFromRGB(0xF44336))
                    }
                    let nombre = dato.isChecked == false ?  "check-empty" : "check-completed"
                    
                    row.isChecked.setImageNamed(nombre)
                    
                    
            }
        }
    }
    
    func UIColorFromRGB(rgbValue: UInt) -> UIColor {
        return UIColor(
            red: CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(rgbValue & 0x0000FF) / 255.0,
            alpha: CGFloat(1.0)
        )
    }
    
    override func table(table: WKInterfaceTable, didSelectRowAtIndex i: Int) {
        
        if let dato = self.datos[i] as? Dato{
            
            if (dato.isChecked != true) {
                self.datos_seleccionados.append(dato)
                print("si \(self.datos_seleccionados.count)")
                print("si")
            } else{
                //let id: Int = self.datos_seleccionados.indexOf(dato)!
                print("si \(self.datos_seleccionados.count)")
                
                let id = find( dato.id!)
                self.datos_seleccionados.removeAtIndex(id)
                print("no \(self.datos_seleccionados.count)")
                
            }
            dato.isChecked = !dato.isChecked!
            setupTable()
        }
        
        print(table)
        
    }
    func find(id:String) ->Int {
        var id_:Int = -1
        for var i = 0; i < self.datos_seleccionados.count; i++ {
            if self.datos_seleccionados[i].id == id {
                id_ = i
                break
            }
        }
        return id_
    }
    
    
    override func didDeactivate() {
        // This method is called when watch view controller is no longer visible
        super.didDeactivate()
    }

}
