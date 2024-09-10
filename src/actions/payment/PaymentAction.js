import axios from "../../axios/axios";


const _addPayment = (payment) => ({
    type: 'ADD_PAYMENT',
    payment
});

export const addPayment = (paymentData = {
    type: '',
    status: '',
    cardName: '',
    cardNumber: '',
    cardExpiry: '',
    cvv: ''
    
}) => {
    return (dispatch) => {
        const payment = {
            type: paymentData.type,
            status: "Success",
            card:
            {
            cardName: paymentData.cardName,
            cardNumber: paymentData.cardNumber,
            cardExpiry: paymentData.cardExpiry,
            cvv: paymentData.cvv
            }
        };
        console.log(payment);
        return axios.post('/addPayment', payment).then(() => {
            dispatch(_addPayment(payment));
        }).catch(error => {
            throw (error);
        });
    };
};

const _updatePayment = (paymentId,updatedPayment) => ({
    type:"UPDATE_PAYMENT",
    paymentId,
    updatedPayment
});


export const updatePayment = (paymentId,updatedPayment) => {
    return (dispatch) => {
        const payment = {
            paymentId:paymentId,
            type: updatedPayment.type,
            status : "Success",
            card:{
                id:updatedPayment.id,
                cardName:updatedPayment.cardName,
                cardNumber:updatedPayment.cardNumber,
                cardExpiry:updatedPayment.cardExpiry,
                cvv:updatedPayment.cvv
            }
        };
        console.log(payment);
        return axios.put(`/updatePayment/${paymentId}`,payment).then(() => {
            console.log(payment)
            dispatch(_updatePayment(paymentId,payment));
        }).catch(error => {
            throw (error);
        });
    }
};
