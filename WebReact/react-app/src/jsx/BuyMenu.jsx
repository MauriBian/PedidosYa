import React from 'react'
import '../css/Home.css'
import axios from 'axios';
import Popup from "reactjs-popup";
import PopUpContent from './PopUpContent'
import PopUpMenus from './PopUpMenus';
export default class BuyMenu  extends React.Component{
    constructor(props){
        super(props) 
        this.comprar = this.comprar.bind(this);
        this.cancelar = this.cancelar.bind(this);
    }
    render(){
        return(
            <div className="card bg-light mb-3">
                <div className="card-header">{this.props.nombre}</div>
                <div className="card-body">
                    <h5 className="card-title">Productos</h5>
                    <ul className = "card-text">
                        {this.props.products.map ((c,i) => { return <li key = {i}> {c.name}   </li> } ) }
                    </ul>
                    <p className="card-text">Valor total = ${this.props.total}</p>
                    { (this.props.comprable)? <button className="btn btn-primary" onClick = {this.comprar}>Comprar</button> : null}
                    
                </div>
            </div>
        )
    }

    comprar(){
        this.props.update({ code : this.props.code ,name: this.props.nombre, products : this.props.products , total : this.props.total})
        alert("Producto agregado a la orden");
    }

    cancelar(){

    }
    

}