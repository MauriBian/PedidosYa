import React from 'react'
import Api from "./Api"

export default class PopUpContent extends React.Component{

    render(){

        return(
            <div className = "navBar">
                <nav className="navbar navbar-expand-lg navbar-dark bg-light">
                    <a className="navbar-brand">Calificar:</a>                   
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mr-auto">
                            <button className="btn btn-primary my-2 my-sm-0"  type="button" onClick = {() => Api.rateDeliver(1,this.props.id).then( r => location.reload()).catch(e => console.log(e)) } >1</button>
                            <button className="btn btn-primary my-2 my-sm-0"  type="button" onClick = {() => Api.rateDeliver(2,this.props.id).then( r => location.reload()).catch(e => console.log("ERROR")) }>2</button>
                            <button className="btn btn-primary my-2 my-sm-0"  type="button" onClick = {() => Api.rateDeliver(3,this.props.id).then( r => location.reload()).catch(e => console.log("ERROR")) }>3</button>
                            <button className="btn btn-primary my-2 my-sm-0"  type="button" onClick = {() => Api.rateDeliver(4,this.props.id).then( r => location.reload()).catch(e => console.log("ERROR")) }>4</button>
                            <button className="btn btn-primary my-2 my-sm-0"  type="button" onClick = {() => Api.rateDeliver(5,this.props.id).then( r => location.reload()).catch(e => console.log("ERROR")) }>5</button>
                            <button className="btn btn-primary my-2 my-sm-0"  type="button" onClick = {() => Api.rateDeliver(6,this.props.id).then( r => location.reload()).catch(e => console.log("ERROR")) }>6</button>
                            <button className="btn btn-primary my-2 my-sm-0"  type="button" onClick = {() => Api.rateDeliver(7,this.props.id).then( r => location.reload()).catch(e => console.log("ERROR")) }>7</button>
                            <button className="btn btn-primary my-2 my-sm-0"  type="button" onClick = {() => Api.rateDeliver(8,this.props.id).then( r => location.reload()).catch(e => console.log("ERROR")) }>8</button>
                            <button className="btn btn-primary my-2 my-sm-0"  type="button" onClick = {() => Api.rateDeliver(9,this.props.id).then( r => location.reload()).catch(e => console.log("ERROR")) }>9</button>
                            <button className="btn btn-primary my-2 my-sm-0"  type="button" onClick = {() => Api.rateDeliver(10,this.props.id).then( r => location.reload()).catch(e => console.log("ERROR")) }>10</button>

                        </ul>
                    </div>
                </nav>  
            </div>
        );
    }

}