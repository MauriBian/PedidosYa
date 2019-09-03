import React from 'react'
import '../css/Home.css'
import Api from '../jsx/Api'
import NavBar from './NavBar'
import SearchResult from './SearchResult'
export default class SearchContainer extends React.Component{

    render(){
        return(
            <div className ="containerSearch" >
                {this.props.query.map ((c,i) => {return <SearchResult key = {i} comprar = {this.props.comprar} user = {this.props.user} {...this.props} user ={this.props.user} code  = {c.code} comprar = {this.props.comprar} name = {c.name}  products = { c. products } description={c.description} {...this.props}></SearchResult> } ) }
            </div>
        )
    }
}