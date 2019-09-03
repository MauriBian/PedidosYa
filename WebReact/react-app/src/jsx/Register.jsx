import React from 'react'
import '../css/Register.css'
import Api from "./Api"
import Alert from "./components/Alert"
export default class Register extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            inputUser : "",
            inputEmail :    "",
            inputPassword : "",
            inputAddress : "",
            inputLocation : ""
        }
        this.alert = React.createRef()
    }

    render(){
        return (

    <div className="background">
    <div className="container">
        <div id="ccc" className="card card-container">
       
        <h5> Registrarse </h5> 
        < Alert ref={this.alert} mensaje="Complete todos los campos"/> 
            <p id="profile-name" className="profile-name-card"></p>
            <form className="form-signin">
                <span id="reauth-email" className="reauth-email"></span>
                
                <input 
                    value={this.state.inputUser}
                    onChange={event => {
                        this.setState({
                            inputUser: event.target.value 
                        })
                    }}  
                    type="usuario"
                    name="userName"
                    id="imputRegister" 
                    className="form-control" 
                    placeholder="Usuario" 
                    required >
                </input>

                <input 
                    value={this.state.inputPassword}
                    onChange={event => {
                        this.setState({
                            inputPassword: event.target.value 
                        })
                    }}  
                    type="password"
                    name="password"
                    id="imputRegister" 
                    className="form-control" 
                    placeholder="contraseña" 
                    required >
                </input>

                <input 
                    value={this.state.inputEmail}
                    onChange={event => {
                        this.setState({
                            inputEmail: event.target.value 
                        })
                    }}  
                    type="email"
                    name="email"
                    id="imputRegister" 
                    className="form-control" 
                    placeholder="eMail" 
                    required >
                </input>

                <input 
                    value={this.state.inputAddress}
                    onChange={event => {
                        this.setState({
                            inputAddress: event.target.value 
                        })
                    }}  
                    type="adress"
                    name="adress"
                    id="imputRegister" 
                    className="form-control" 
                    placeholder="direccion" 
                    required >
                </input>

                <input 
                    value={this.state.inputCuidad}
                    onChange={event => {
                        this.setState({
                            inputCuidad: event.target.value 
                        })
                    }}  
                    type="cuidad"
                    name="cuidad"
                    id="imputRegister" 
                    className="form-control" 
                    placeholder="cuidad" 
                    required >
                </input>
                <button
                    className="btn btn-lg btn-primary btn-block btn-signin" 
                    type="button" 
                    onClick={() =>
                        this.Register()
                    } > Aceptar
                </button>
                <button
                    className="btn btn-lg btn-primary btn-signin" 
                    type="button" 
                    onClick={() =>
                        this.props.history.push('/')
                    } > Cancelar
                </button>

            </form>
        </div>
    </div>
    </div>
    )}

    Register(){
        const obj = { userName: this.state.inputUser, email: this.state.inputEmail, password: this.state.inputPassword, address : this.state.inputAddress, coord : {
            latitude : 12,
            longitude: 12,
        } };

        Api.Register(obj)
        .then(res=> {
                this.props.history.push('/')
        })
        .catch((error) =>{
            this.alert.current.mostrar()
        })
    }

    
}