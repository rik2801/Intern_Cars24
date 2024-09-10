const PaymentValidation = {
    cardName: {
      rules: [
        {
          test: /^[a-zA-Z ]*$/,
          message: 'Card Name must contain only alphabets characters',
        },
        {
          test: (value) => {
            return value.length > 3;
          },
          message: 'Card Name must be longer than three characters',
        },
      ],
      errors: [],
      valid: false,
      state: '',
    },
    cardNumber: {
      rules: [
        {
          test: (value) => {
            return value.length === 16;
          },
          message: 'CardNumber should be equal to 16 digit',
        },
        {
            test: /^[0-9]*$/,
            message: 'Card Number must contain only numeric digit',
          },
      ],
      errors: [],
      valid: false,
      state: ''
    },
    cvv: {
        rules: [
          {
              test: /^[0-9]*$/,
              message: 'Card Cvv must contain only numeric digit',
            },
            {
                test: (value) => {
                  return value.length === 3;
                },
                message: 'Card Cvv must contain only 3 numeric digit',
              },

        ],
        errors: [],
        valid: false,
        state: ''
      },
      cardExpiry: {
        rules: [
          {
            test: (value) => {
              let currentDate = new Date("2021-05-07");
              let inputDate = new Date(value);
              return inputDate > currentDate; 
            },
            message: 'Card Expiry date must be greater than current date',
          },
        ],
        errors: [],
        valid: false,
        state: ''
      },
      id : {
        rules: [
          {
              test: /^[0-9]*$/,
              message: 'Card Id must contain only numeric digit',
            },
        ],
        errors: [],
        valid: false,
        state: ''
      },
  };
  
  export default PaymentValidation;