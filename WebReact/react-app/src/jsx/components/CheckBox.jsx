import React from "react"
import "../../css/BuyConfirmation.css"
import "./Cash"
import Cash from "./Cash";
import CreditCard from "./CreditCard"
import MercadoPago from "./MercadoPago"
export default class Checkbox extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            checked : false ,
            render : ""
         
        }
      this.selectedPM = "Cash"
      this.handleChangeCreditCard = this.handleChangeCreditCard.bind(this)
      this.handleChangeCash = this.handleChangeCash.bind(this)
      this.handleChangeMercadoPago = this.handleChangeMercadoPago.bind(this)
    }   

    render(){
        return (<div>
                <input value="boton" onChange={this.handleChangeCash} className="Cash" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"/>
                <label className="form-ch" htmlFor="inlineRadio1">Cash</label>
                <input value="boton" onChange={this.handleChangeCreditCard} className="CreditCard" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"/>
                <label className="form-check-label" htmlFor="inlineRadio1">Credit Card</label> 
                <input  value="boton" onChange={this.handleChangeMercadoPago} className="MercadoPago" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"/>
                <label className="form-check-label" htmlFor="inlineRadio1">Mercado Pago</label>  
            
                {this.state.render}
              
                </div>
                )
    }

    handleChangeCash(){
        this.renderCash()
        this.props.setPM ("Cash")
    }
    handleChangeCreditCard(){
        this.renderCreditCard()
        this.props.setPM ("CreditCard")
    }

    handleChangeMercadoPago(){
        this.renderMercadoPago()
        this.props.setPM ("MercadoPago")
    }


    renderCash(){
       
             this.setState({
               render : <Cash> </Cash>
            })
        
    }
    
    renderCreditCard(){
      
            this.setState({
                render : <CreditCard></CreditCard>
            })
        
    }

    
    renderMercadoPago(){

            this.setState({
                render : <MercadoPago></MercadoPago>
            })
        
    }




}
