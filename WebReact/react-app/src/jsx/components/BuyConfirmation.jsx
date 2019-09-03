import React from "react"
import "../../css/BuyConfirmation.css"
import CheckBox from "./CheckBox"
export default class BuyConfirmation extends React.Component{
  constructor(props){
    super(props)
    this.state={
        name : "",
        selectedPM : "Cash", //El metodo de pago
    }

    this.setPM=this.setPM.bind(this)
  }
  setPM(pm){
    this.setState({selectedPM : pm })
    
  }

  componentDidMount(){
    this.setState({name : this.props.name })
  }
  
    render(){
        return(
          
          <div id="cbc" className="card" style={{width : "60%"}}>
            <div className="card-body">
            <h5 className="titulo">Confirmacion de compra</h5>
            <h6 className="card-subtitle mb-2 text-muted">Confirme su compra</h6>
            <p id="ctcb" className="card-text">El monto total gastado en {this.state.name} es de ${this.props.total}</p>
            <p id="ctcb2" className="card-text2">Seleccione su metodo de pago </p>
            <CheckBox setPM={this.setPM}> </CheckBox>

            <button id="boton" className="btn btn-lg btn-primary btn-block btn-signin" type="button" onClick = { () => this.props.funcMetodo(this.state.selectedPM)} >Confirmar</button>
           </div>
          </div>

         )
    }

 



}

