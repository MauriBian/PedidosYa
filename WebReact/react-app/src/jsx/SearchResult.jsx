import React from 'react'
import '../css/Home.css'
import Api from './Api'

export default class Orders  extends React.Component{
    constructor(props){
        super(props) 
        this.entrar = this.entrar.bind(this);
        this.comprar = this.comprar.bind(this);
        this.state = { user : {}}
    }

    componentDidMount(){
        this.setState( { user : this.props.user} )
    }

    render(){
        return(
            <div className="card bg-light mb-3" >
                <div className="card-header font-weight-bold">{this.props.name}</div>
                <div className="card-body">
                { (this.props.products != null) ? <h5 className="card-title">Productos:</h5> : <h5 className="card-title">Descripcion:</h5> }
                <h6 className="card-text">{this.props.description}</h6>
                    <ul className = "card-text">
                        { (this.props.products != null) ? this.props.products.map ((c,i) => { return <li key = {i}> {c.name}   </li> } ): "" }
                    </ul>
                    { (!this.props.comprar) ? <button  id = "bComprar" className ="btn btn-primary dim btn-large-dim" type="button" onClick={ () => this.entrar(this.props.code)}><i class="fa fa-check"></i> &nbsp; &nbsp;Entrar</button> : <button id = "bComprar" className ="btn btn-primary dim btn-large-dim" type="button" onClick= { () => this.comprar(this.props.code)}> <i class="fa fa-money"></i> Comprar</button>}
                </div>
            </div>
        )

        
    }
    
    entrar(id){

        this.props.history.push('/buy?id=' + id,this.state.user )
    }

    comprar(){
        const orden = { code : this.props.code ,name: this.props.nombre, products : this.props.products , total : this.props.total}
        Api.getRestByMenuId(this.props.code)
        .then (r =>this.props.history.push('/buy?id=' + r.data.code + "&mcode=" + orden.code,this.state.user ) )
        //this.props.history.push('/buy?id=' + id,this.state.user )
    }
}