import React from 'react'
import '../css/Home.css'
import Api from './Api'
import { withRouter  } from 'react-router-dom';
import Search from './Search'

export default class NavBar extends React.Component{

    constructor(props){
        super(props)
        this.search = this.search.bind(this);
        this.home = this.home.bind(this);
        this.state = { searchInput : "",
        user : this.props.location.state,
        image : ""
     }

    }

    

    render(){

        return(
            <div className = "navBar">
                <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <button className="btn btn-outline-light" type="button" onClick={this.home}>PlanetaYa</button>                  
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mr-auto">
                            <input className="form-control mr-sm-2" type="search" placeholder="Busqueda..." aria-label="Search" value={this.state.searchInput} onChange={event => { this.setState({ searchInput: event.target.value })}}></input>
                            <button className="btn btn-light my-2 my-sm-0"  type="submit" onClick = {this.search} >Buscar</button>
                        </ul>
                        <form className="form-inline my-2 my-lg-0">
                            
                        <button className="btn btn-outline-light" type="button">{this.state.user.userName}</button>
                        <div className="profile-header-img">
                            <img className="rounded-circle" src={this.state.image} />&nbsp;&nbsp;
                        </div>
                        <button className="btn btn-outline-light" type="button"  onClick={() =>this.props.history.push('/')} > Salir</button>
                        </form>
                    </div>
                </nav>  
            </div>
        );
    }

    componentDidMount(){
        Api.getRandomImage().then(urlImage => {
            this.setState({
                image : urlImage
            })
        })
     }

    home(){
        this.props.history.push('/home',this.state.user);
    }



    search (){
        const q = this.state.searchInput;
        this.props.history.push('/search?q=' + q ,this.state.user)
        if ( this.props.location.pathname ==  "/search"){
            window.location.reload();
        }
    }

}


