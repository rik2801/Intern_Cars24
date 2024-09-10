const CustomerValidation = {
    name: {
      rules: [
        {
          test: /^[a-zA-Z ]*$/,
          message: 'Customer Name must contain only alphabets characters',
        },
        {
          test: (value) => {
            return value.length > 3;
          },
          message: 'Customer Name must be longer than three characters',
        },
      ],
      errors: [],
      valid: false,
      state: '',
    },
    contactNo: {
      rules: [
        {
          test: (value) => {
            return value.length === 10;
          },
          message: 'Contact Number should be equal to 10 digits',
        },
        {
            test: /^[0-9]*$/,
            message: 'Contact Number must contain only numeric values',
          },
      ],
      errors: [],
      valid: false,
      state: ''
    },
    
    dob: {
        rules: [
          {
            test: (value) => {
              let currentDate = new Date("2003-01-01");
              let inputDate = new Date(value);
              return inputDate < currentDate; 
            },
            message: 'Date of Birth must be lesser ',
          },
        ],
        errors: [],
        valid: false,
        state: ''
      },
    pinCode: {
        rules: [
          {
            test: (value) => {
              return value.length === 6;
            },
            message: 'Pin Code should be equal to 6 digits',
          },
          {
              test: /^[0-9]*$/,
              message: 'Pin Code must contain only numeric values',
            },
        ],
        errors: [],
        valid: false,
        state: ''
      },
      
  };
  
  export default CustomerValidation;