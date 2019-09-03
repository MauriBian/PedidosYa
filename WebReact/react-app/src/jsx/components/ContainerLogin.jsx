import React from "react"
import '../../css/Login.css'
import Api from "../Api"
import { Link } from 'react-router-dom';
import Alert from "./Alert"

export default class ContainerLogin extends React.Component{
constructor(props){
    super(props)
    this.state={ inputUser : "" , inputPass : "" }
    this.alert = React.createRef()
}

render(){
    return(
    <div className="container">
        <div id= "ccc" className="card card-container">
        < Alert ref={this.alert} mensaje ="Usuario o contraseña incorrecta"/>

            <img className="profile-img-card" src="http://www.eds.org.nz/assets/resized/img/head%20and%20shoulders%20image%20male-300-300-300-300-crop.png?k=214bac0bdb" alt="" ></img>
            <p id="profile-name" className="profile-name-card"></p>
            <form className="form-signin">
                <span id="reauth-email" className="reauth-email"></span>
                <input  value={this.state.inputUser} onChange={event => { this.setState({ inputUser: event.target.value })}}  type="usuario" id="inputEmail" className="form-control" placeholder="Usuario" required ></input>
                <input value={this.state.inputPass} onChange={event => { this.setState({ inputPass: event.target.value })}}   type="password" id="inputPassword" className="form-control" placeholder="Contraseña" required></input>

                <button className="btn btn-lg btn-primary btn-block btn-signin" type="button" onClick={() => this.LogIn()} >Sign in</button>
            </form>
            <Link className="forgot-password" to="/">Forgot Password?</Link>
            <Link className="forgot-password" to="/register">Register</Link>
        </div>
    </div>
    )
        
}


LogIn(){
    const obj = { username: this.state.inputUser, password: this.state.inputPass };
    Api.LogIn(obj).then(res => {
    
        if (res.status == 202){
            this.props.history.push('/home',res.data);
          
        }
        
    })
    .catch((error) => {
        this.alert.current.mostrar()
    });
}


}