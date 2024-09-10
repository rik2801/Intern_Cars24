import axios from "../../axios/axios";


const _addCustomer = (customer) => ({
    type: 'ADD_CUSTOMER',
    customer
});

export const addCustomer = (customerData = {
    name : "",
    email: "",
    contactNo: "",
    dob: "",
    doorNo: "",
    street: "",
    area: "",
    city: "",
    state: "",
    pinCode: ""
    
}) => {
    return (dispatch) => {
        const customer = {
            name: customerData.name,
            email: customerData.email,
            contactNo: customerData.contactNo,
            dob: customerData.dob,
            address:
            {
            doorNo: customerData.doorNo,
            street: customerData.street,
            area: customerData.area,
            city: customerData.city,    
            state: customerData.state,
            pinCode: customerData.pinCode,
            
            }
        };
        console.log(customer);
        const result =  axios.post('/addCustomer', customer);
        dispatch(_addCustomer(result.data));
    };
};

const _removeCustomer = ({userId} = {}) => ({
    type: 'REMOVE_CUSTOMER',
    userId
});

export const removeCustomer = ({userId} = {}) => {
    return (dispatch) =>  {
        return axios.delete(`/removeCustomer/${userId}`).then(() => {
            dispatch(_removeCustomer({userId}));
        });
    };
};

const _updateCustomer = (updatedCustomer) => ({
    type:"UPDATE_CUSTOMER",
    updatedCustomer
});

export const updateCustomer = (updatedCustomer) => {
   
 {
    return (dispatch) => {
        const customer = {
            userId:updatedCustomer.userId,
            name: updatedCustomer.name,
            email : updatedCustomer.email,
            contactNo : updatedCustomer.contactNo,
            dob : updatedCustomer.dob,
            address:{
                doorNo:updatedCustomer.doorNo,
                street:updatedCustomer.street,
                area:updatedCustomer.area,
                city:updatedCustomer.city,
                state:updatedCustomer.state,
                pinCode:updatedCustomer.pinCode

            }
        };
        console.log(customer);
        return axios.put(`/updateCustomer`,customer).then(() => {
            dispatch(_updateCustomer(customer));
          
        }).catch(error => {
            throw (error);
        });
    }
};
}
