
import axios from "../../axios/axios";


const _addUser = (user) => ({
    type: 'ADD_USER',
    user
});

export const addUser = (userData = {
    username :'',
    password: '',
    role: ''
    
}) => {
    return (dispatch) => {
        const user = {
            username: userData.username,
            password: userData.password,
            role: userData.role
        };
        console.log(user);
        return axios.post('/addUser', user).then((response) => {
            console.log(response)
            dispatch(_addUser(user));
        }).catch(error => {
            throw (error);
        });
    };
};
