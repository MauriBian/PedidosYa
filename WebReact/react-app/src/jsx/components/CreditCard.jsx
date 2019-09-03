import React from "react"
export default class CreditCard extends React.Component{

    render(){return(
        <div>
            <br></br>
            <br></br>
            <input  type="usuario" id="inputEmail" className="form-control" placeholder="Nombre y apellido del titular" required ></input>
            <br></br>
           <form>
                <div className="row">
                    <div className="col">
                        <input type="text" className="form-control" placeholder="Numero de la tarjeta"/>
                    </div>
                <div className="col">
                        <input type="text" className="form-control" placeholder="CSV"/>
                    </div>
                </div>
             </form>
             <br></br>
             <input  type="usuario" id="inputEmail" className="form-control" placeholder="Vencimiento" required ></input>
             <br></br>
             <br></br>
        </div>)
    }
}