import React from "react"
export default class Parcial extends React.Component{
    constructor(props){
        super(props)
    }
    render(){
        return(
            <div>
                {this.grid()}
                {this.grid()}
            </div>
            )
    }


     tarjeta(nombre){
        return(
            
            <div class="card text-center" >
                <div class="card-header">
                {nombre}
                </div>
                <div class="card-body">
                    <center><h5 class="card-title">Special title treatment</h5></center>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
                <div class="card-footer text-muted">
                  2 days ago
                 </div>
            </div>
       
    
        )
    }



    grid(){
        return(
        <div class="container">
            <div class="row">
                <div class="col-sm">
                   {this.tarjeta()}
                </div>
            <div class="col-sm">
                    {this.tarjeta()}
            </div>
            <div class="col-sm">
                    {this.tarjeta()}
            </div>
        </div>
      </div>)
    }

}

