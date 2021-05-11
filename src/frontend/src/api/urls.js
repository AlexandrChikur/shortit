export default function (instance) {
    return {
        getShorterUrl(payload){
            return instance.get('/r', {params: payload});
        },
        getUrlInfo(payload){
            return instance.get('/r/info', {params: payload})
        }
    }
}