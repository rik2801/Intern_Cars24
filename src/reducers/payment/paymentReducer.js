import 'bootstrap/dist/css/bootstrap.min.css';

const paymentReducerDefaultState = [];

const paymentReducer =  (state = paymentReducerDefaultState, action) => {
    switch (action.type) {
        case 'ADD_PAYMENT':
            return [
                ...state,
                action.payment
            ];

        case 'UPDATE_PAYMENT':
            return state.map((payment,paymentId) => {
                if (paymentId === action.paymentId){
                    return {
                        ...state,
                        ...action.payment
                    };
                }else {
                    return payment;
                }
            });

        default:
            return state;
    }
}
export default paymentReducer;
