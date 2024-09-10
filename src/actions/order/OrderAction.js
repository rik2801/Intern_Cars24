import axios from "../../axios/axios";


const _addOrder = (order) => ({
    type: 'ADD_ORDER',
    order
});

export const addOrder = (orderData = {
    amount: '',
    billingDate: '',
    userId: '',
    paymentId: ''
}) => {
    return (dispatch) => {
        const order = {
            amount: orderData.amount,
            billingDate: orderData.billingDate,
            customer:
            {
            userId: orderData.userId,
            },
            payment:
            {
            paymentId: orderData.paymentId
            }
        
    
        };
        console.log(order);
        return axios.post('/addOrder', order).then(() => {
            dispatch(_addOrder(order));
        }).catch(error => {
            throw (error);
        });
    };
};




