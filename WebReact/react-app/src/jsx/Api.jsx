import axios from 'axios';
const url = "http://192.168.0.13:7000/";
const url2 = "https://dog.ceo/api/breeds/image/random"


const LogIn = function(obj){

   return axios.post(url + 'login',   obj )   
}
const Register = function(obj){
    return axios.post(url + 'register',obj)
}

const Calif = function(amount){
    axios.post(url+'deliver/' + "0",{ rating : amount })
    .then(res => {console.log(res)} )
    .catch( (e) => {console.log(e)})
}

const Search = function (getUrl){
    return axios.get (getUrl)
}

   
const getUserById = function(idUser){
    return axios.get(url + 'users/' + idUser)
}

const getUserByIdWithOrders = function(idUser){
    return axios.get(url + 'users/' + idUser + '?include=orders')
}

const Deliver = function(obj){
    return axios.post(url + 'deliver',obj)
}

const rateDeliver = function(obj,id){
    console.log(url + 'deliver/' + id);
    const bori = { rating : obj }
    return axios.put(url + 'deliver/' + id,bori)
}

const searchByQuery = function(data,lat,long){
    if (lat  == undefined || long == undefined){
        console.log(url + 'search?q=' + data );
        return axios.get(url + 'search?q=' + data )
    }
    else{
        return axios.get(url  + 'search?q=' + data + '&lat=' + lat + '&long=' + long )
    }
    
}

const getRestById = function(id){
    const urldef = url + 'restaurant/' + id
    console.log(urldef)
    return axios.get(urldef)
}

const getRandomImage = function(){
    return axios.get(url2).then(res => res.data.message)
}

const getDeliver = function(id){
    return axios.get(url + "deliver/" + id)
}

const getRestByMenuId = function(id){
    return axios.get(url + "menu/" + id)
}

const getTotal = function (productos){
    return productos.reduce((previus,actual) => previus+=actual.price,0)
}

module.exports = {
    LogIn,
    Register,
    getUserById,
    getUserByIdWithOrders,
    Deliver,
    rateDeliver,
    searchByQuery,
    getRestById,
    getRandomImage,
    getDeliver,
    getRestByMenuId,
    getTotal
}
