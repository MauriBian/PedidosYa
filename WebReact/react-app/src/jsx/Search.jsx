import React from 'react'
import '../css/Home.css'
import Api from './Api'
import NavBar from './NavBar'
import SearchContainer from './SearchContainer'
import queryString from 'query-string';

export default class Search extends React.Component{
    constructor(props){
        super(props);
        this.state= { //ordenes de prueba
           restaurantes : [ ],
           menus : [ ],
           user : {}
        }
        this.search=this.setEstado.bind(this);
        this.setEstado();
    }

    setEstado(){
        let url=this.props.location.search;
        let params=queryString.parse(url);
        Api.searchByQuery(params.q)
        .then ((r) => {this.setState( { restaurantes : r.data.Restaurants, menus : r.data.Menus })} )
        .catch ( e => console.log(e))
       
       
    }

 
        
    componentDidMount(){
        this.setState({
            user : this.props.location.state  
          })
    }
    


    render(){
        return(
            <div className="app">
            <div className="p-3 mb-2 bg-light text-dark">
                <NavBar user={this.state.user} {...this.props} searchPage={true}/>
                <center><p className="h2">Restaurantes</p></center>
                <SearchContainer comprar={false} user={this.state.user} query={this.state.restaurantes} {...this.props}></SearchContainer>
                <center><p className="h2">Menues</p></center>
                <SearchContainer comprar={true} user={this.state.user} query={this.state.menus} {...this.props}></SearchContainer>
            </div>
            </div>
        );
    }
}