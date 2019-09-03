import React from 'react'
import '../css/Login.css'
import ContainerLogin from './components/ContainerLogin'
export default class LogIn  extends React.Component{
 
    render(){
        return(
        <div className="background">
            <div >
                <h1>
                    Bienvenido a PlanetaYA!
                </h1>
                <ContainerLogin {...this.props}/> 
            </div>
        </div>
        )
    }    
}
  
