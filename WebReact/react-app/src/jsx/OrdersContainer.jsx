import React from 'react'
import '../css/Login.css'
import Orders from './Orders'
import Api from './Api'
export default class OrdersContainer  extends React.Component{

 
   
    render(){
        return(
            <div className ="container" >
                {this.props.orders.map ((c,i) => { return <Orders 
                                                                  key = {i}
                                                                  id ={c.code}
                                                                  calificable = {this.props.calificable}
                                                                  nombre = {c.restaurant.name}
                                                                  total = { 100 }
                                                                  menus = { c. menus }
                                                                  restaurant = { c.restaurant}>
                                                           </Orders> } ) }
            </div>
        )
    }
    

}
  
