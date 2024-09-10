const customerReducerDefaultState = [];

const customerReducer =  (state = customerReducerDefaultState, action) => {
    switch (action.type) {
        case 'ADD_CUSTOMER':
            return [
                ...state,
                action.customer
            ];
        case 'REMOVE_CUSTOMER':
            return state.filter(({userId}) => userId !== action.userId);
        case 'UPDATE_CUSTOMER':
            return [
                ...state,
                action.customer
            ];
            
        case 'GET_CUSTOMERS':
            return [
                    ...state,
                    action.customers
                ];
        case 'GET_CUSTOMER':
            return [
                    ...state,
                    action.customer
                ];
        default:
            return state;
    }
}

export default customerReducer;