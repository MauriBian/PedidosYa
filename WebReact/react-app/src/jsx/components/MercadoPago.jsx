import React from "react"
export default class MercadoPago extends React.Component{

    render(){return(
        <div>
        <br></br>
        <center><img  className = "imagen" src="https://www.endeavor.org.ar/wp-content/uploads/2017/05/web-Mercado-Pago.jpg"  height="80" width="250" alt="" ></img></center>
        <br></br>
        <br></br>
        <form>
            <div className="row">
                <div className="col">
                    <input type="text" className="form-control" placeholder="Usuario"/>
                </div>
            <div className="col">
                    <input type="text" className="form-control" placeholder="Contraseña"/>
                </div>
            </div>
         </form>
         <br></br>

         <br></br>
         <br></br>
    </div>)
    }
}