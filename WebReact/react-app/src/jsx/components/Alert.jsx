import React from 'react'

export default class Alert extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            render : false
        }
    }

    mostrar(){
       this.setState({
           render : true
       })
    }
    render(){
        if (this.state.render == true){
            return(
             <div  className="alert alert-danger" role="alert">
                  {this.props.mensaje}
            </div>)
        }
        else{
            return(<div></div>)
        }
    }
}