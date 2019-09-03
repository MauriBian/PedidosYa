import React from 'react'
import '../css/Home.css'
import Popup from "reactjs-popup";
import PopUpContent from './PopUpContent'
import PopUpMenus from './PopUpMenus';
import Api from "./Api"
export default class Orders  extends React.Component{
    constructor(props){
        super(props)
        this.state={
            rate : 0
        } 
        this.getTotal = this.getTotal.bind(this);
    }

    componentDidMount(){
        this.getRating().then(resp => this.setState({
            rate : resp.data.second.rating
        }))
    }
    render(){
        return(
            <div className="card bg-light mb-3">
                <div className="card-header font-weight-bold">{this.props.nombre}</div>
                <div className="card-body">
                    <ul className="card-text">
                        {this.props.menus.map ((c,i) => { return <li key={i}> {c.name} (${c.valor})  </li> } ) }
                    </ul>
                    <p className="card-text">Valor total: ${this.getTotal()}</p>
                    <Popup trigger={<button className="btn btn-primary">+Info</button>} modal closeOnDocumentClick>
                            <PopUpMenus id={this.props.restaurant.code}></PopUpMenus>
                    </Popup>
                    {(this.props.calificable) ? 
                        <Popup trigger={<button className="btn btn-primary">Calificar</button>} modal closeOnDocumentClick>
                            <PopUpContent id={this.props.id}></PopUpContent>
                        </Popup>
                    : null }
                  <label className="Calificacion" >Calificacion: {this.state.rate}</label>
                </div>
            </div>
        )
    }
    

    getRating(){
       return Api.getDeliver(this.props.id)
    }

    getTotal(){
        return Api.getTotal(this.props.menus.map( x => x.products).flat());
    }
}
  
