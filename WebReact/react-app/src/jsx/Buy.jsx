import React from 'react'
import '../css/Home.css'
import Api from './Api'
import NavBar from './NavBar'
import queryString from 'query-string';
import BuyMenuContainer from './BuyMenuContainer';
import BuyConfirmation from './components/BuyConfirmation'
import Popup from "reactjs-popup";

export default class Buy extends React.Component{
    constructor(props){
        super(props);
        this.state={ 
            menus : [],
            ordered : [],
            name : "",
            paymentMethod : "Cash",
            user : this.props.location.state.user
        }
        this.getMenus=this.getMenus.bind(this);
        this.updateOrders=this.updateOrders.bind(this);
        this.finalizarCompra=this.finalizarCompra.bind(this);
        this.setMetodoDePago=this.setMetodoDePago.bind(this);
        this.getTotales = this.getTotales.bind(this);
        setTimeout(() => {
            this.getMenus();

        }, 500);
        
    }

    getMenus(){
        let url=this.props.location.search;
        let params=queryString.parse(url);
        Api.getRestById(params.id)
        .then ( (res) =>{ this.setState({menus : res.data.menus, name : res.data.name  } )
        if (params.mcode != undefined){
            this.updateOrders(this.state.menus.find ( x =>  x.code == params.mcode));
        }
    })
        .catch(e => console.log(e))
        

    }


    render(){
        return(
            <div className="p-3 mb-2 bg-light text-dark">
                <NavBar {...this.props} user={this.props.location.state.user}></NavBar>
                <center><h2 className ="h2">{this.state.name}</h2></center>
                <center><h3 className ="h3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pedidos pendientes</h3></center>
                <BuyMenuContainer menus={ this.state.ordered } update = {this.updateOrders} comprable={false}> </BuyMenuContainer>
                <Popup trigger={<button id = "finalizar"className="btn btn-primary">FinalizarCompra</button>} modal closeOnDocumentClick>
                            <BuyConfirmation total = {this.getTotales()} funcMetodo={this.setMetodoDePago} name={this.state.name}></BuyConfirmation>
                </Popup>
                <center><h3>Menues disponibles</h3></center>
                <BuyMenuContainer menus={this.state.menus} update={this.updateOrders} comprable={true}> </BuyMenuContainer>
            </div>
        );
    }

    updateOrders(orders){
        const ordenes = this.state.ordered;
        ordenes.push(orders)
        this.setState({ordered : ordenes })
    }

    finalizarCompra(){
        let url = this.props.location.search;
        let params = queryString.parse(url);
        const menuses = this.state.ordered.map( x => ( {menuId: x.code, amount : 1}))
        let body = { customerName : this.props.location.state.userName, restaurantId : parseInt(params.id) , menus : menuses, paymentMethod : "Cash" , destination : {latitude : 10, longitude : 10, name : "Varela"}}
        Api.Deliver(body);
        this.props.history.push ('/home',this.props.location.state  );
    }

    setMetodoDePago(metodo){
        this.setState({paymentMethod : metodo})
        this.finalizarCompra();
    }

    getTotales(){
        return Api.getTotal(this.state.ordered.map(x => x.products).flat())
    }
}

	