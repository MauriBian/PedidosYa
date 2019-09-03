import React from 'react'
import '../css/Login.css'
import axios from 'axios';
import Orders from './Orders'
import BuyMenu from './BuyMenu'
import Api from './Api'
export default class BuyMenuContainer  extends React.Component{

 
   
    render(){
        return(
            <div className ="container" >
                {this.props.menus.map ((c,i) => { return <BuyMenu key = {i} comprable ={ this.props.comprable} update = {this.props.update} code ={c.code} nombre = {c.name} total = { Api.getTotal(c.products)} products = { c. products }></BuyMenu> } ) }
            </div>
        )
    }
    

}