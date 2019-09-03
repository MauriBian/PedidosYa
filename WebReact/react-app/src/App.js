import React, { Component } from 'react';
import './App.css';
import { Route, BrowserRouter as Router } from 'react-router-dom'
import Login from "./jsx/Login"
import Home from "./jsx/Home"
import Search from "./jsx/Search"
import Register from './jsx/Register';
import Buy from './jsx/Buy';
import Parcial from "./jsx/Parcial"
import BuyConfirmation from "./jsx/components/BuyConfirmation"
class App extends Component {
  render() {
    return (
      <Router>
        <Route path="/search" exact component={Search} />
          <Route path="/home" component={Home} />
          <Route path="/" exact component={Login} />
          <Route path="/register" component={Register}/>  
          <Route path="/buy" component={Buy}/>  
          <Route path="/bc" component={BuyConfirmation}/>
          <Route path="/parcial" component = {Parcial}/>
      </Router>
    )
  }
}

export default App;
