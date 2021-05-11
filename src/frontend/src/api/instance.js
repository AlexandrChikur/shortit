import axios from 'axios'



const instance = axios.create({
    baseURL: "http://127.0.0.1:8000/",
    headers: {
        accept: 'application/json',
        'Access-Control-Allow-Origin': "http://127.0.0.1:8001",

    },
})

export default instance