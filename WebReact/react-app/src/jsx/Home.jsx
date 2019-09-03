import React from 'react'
import '../css/Home.css'
import Api from "../jsx/Api"
import NavBar from './NavBar'
import OrdersContainer from './OrdersContainer'
export default class Home extends React.Component{

    constructor(props){
        super(props);

        this.state = { //ordenes de prueba
            ordenes : [],
            ordenesPendientes: [],
            user : {userName : ""} 
        }    
    }

    componentDidMount(){
     


       this.setState({
         user : this.props.location.state  
       })
       setTimeout(() => {
           let id=this.state.user.code;
            Api.getUserByIdWithOrders(id).then(
            resp => {this.setState({user : resp.data});
            
            const entregadas = resp.data.orders.filter ( x => x.state === "DELIVERED")
            const noEntregadas = resp.data.orders.filter ( x => x.state === "PENDING")
            this.setState({ ordenes : entregadas , ordenesPendientes : noEntregadas})}
        )},500);
        
    }

  

    render(){

        return(
            <div className="p-3 mb-2 bg-light text-dark">
                <NavBar user={this.state.user} {...this.props}></NavBar>
                <center><h2>Pedidos pendientes</h2></center>
                <OrdersContainer orders={this.state.ordenesPendientes} > </OrdersContainer>
                <center><h2>Pedidos entregados</h2></center>
                <OrdersContainer orders={this.state.ordenes} calificable={true}> </OrdersContainer>
            </div>
        );
    }
}


