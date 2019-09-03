import React from 'react'
import Api from "./Api"

export default class PopUpMenus extends React.Component{

    constructor(props){
        super(props)
        this.getInfo = this.getInfo.bind(this);
        this.state = {
            name : "",
            menus : []
        }
        this.getInfo();
    }

    render(){

        return(
            <div className="card text-dark bg-light mb-3">
                <div className="card-header">{this.state.name}</div>
                    <div className="card-body">
                        <h6 className="card-subtitle mb-2 text-muted">Menues:</h6>
                        <ul className = "card-text">
                            {this.state.menus.map ((c,i) => { return <li key = {i}> {c.name} (${Api.getTotal(c.products)})  </li> } ) }
                        </ul>

                </div>
            </div>
        );
    }

    getInfo(){
        Api.getRestById(this.props.id)
        .then( r => {
            this.setState( { name : r.data.name, menus : r.data.menus})
            console.log(this.state)
        })
        .catch(e => console.log(e.message))
    }

}