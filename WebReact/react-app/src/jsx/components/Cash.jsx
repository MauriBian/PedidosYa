import React from "react"
export default class Cash extends React.Component{

    render(){return(
        <div>
        <br></br>
        <br></br>
        <center><label htmlFor="exampleFormControlSelect1">Moneda</label></center>
             <select className="form-control" id="exampleFormControlSelect1">
            <option>ARS</option>
            <option>Dolar</option>
            <option>Euro</option>
            <option>Yen</option>
            <option>Libra esterlina</option>
            <option>Franco suizo</option>
            <option>Rublo</option>
            <option>Bolívar</option>
            <option>Guaraní</option>
        </select>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>

        </div>)
    }
}